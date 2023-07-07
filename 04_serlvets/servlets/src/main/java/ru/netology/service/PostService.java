package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    if(post.getId() == 0) {
      return repository.save(post);
    } else {
      return repository.update(post);
    }
  }

  public Post removeById(long id) {
    var removedPost = repository.getById(id).orElseThrow(NotFoundException::new);
    repository.removeById(id);
    return removedPost;
  }
}

