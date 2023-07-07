package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.dto.PostDtoIn;
import ru.netology.dto.PostDtoOut;
import ru.netology.exception.NotFoundException;
import ru.netology.mapping.PostMapper;
import ru.netology.model.Post;
import ru.netology.model.Status;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
  private final PostRepository repository;
  private final PostMapper mapper;

  @Autowired
  public PostService(PostRepository repository, PostMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public PostDtoOut getById(long id) {
    var post = repository.getById(id).orElseThrow(NotFoundException::new);
    if(!isActivePost(post))
      throw new NotFoundException();
    return mapper.toPostDtoOut(post);
  }

  public PostDtoOut save(PostDtoIn postDtoIn) {
    var out = repository.save(mapper.toPost(postDtoIn));
    return mapper.toPostDtoOut(out);
  }

  public PostDtoOut update(long id, PostDtoIn postDtoIn) {
    var post = repository.getById(id).orElseThrow(NotFoundException::new);
    if(!isActivePost(post))
      throw new NotFoundException();
    post.setId(post.getId());
    post.setContent(postDtoIn.getMessage());
    post.setAuthor(postDtoIn.getAuthor());
    return mapper.toPostDtoOut(repository.update(post));
  }

  public PostDtoOut removeById(long id) {
    var removedPost = repository.getById(id).orElseThrow(NotFoundException::new);
    removedPost.setStatus(Status.REMOVED);
    repository.update(removedPost);
    return mapper.toPostDtoOut(removedPost);
  }

  public List<PostDtoOut> allActive() {
    return repository.all().stream()
            .filter(this::isActivePost)
            .map(mapper::toPostDtoOut)
            .collect(Collectors.toList());
  }

  private boolean isActivePost(Post post) {
    return post.getStatus() == Status.ACTIVE;
  }
}

