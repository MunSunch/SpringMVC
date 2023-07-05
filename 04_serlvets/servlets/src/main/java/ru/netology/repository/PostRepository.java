package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
@Repository
public class PostRepository {
  private ConcurrentSkipListSet<Post> db;
  private static AtomicLong counter;

  public PostRepository() {
    counter = new AtomicLong(1);
    db = new ConcurrentSkipListSet<>((x,y)-> (int) (x.getId()-y.getId()));
      db.add(new Post(counter.getAndIncrement(), "post#1"));
      db.add(new Post(counter.getAndIncrement(), "post#2"));
      db.add(new Post(counter.getAndIncrement(), "post#3"));
  }

  public List<Post> all() {
    return new ArrayList<>(db);
  }

  public Optional<Post> getById(long id) {
    return db.stream()
            .filter(x -> x.getId()==id)
            .findFirst();
  }

  public Post save(Post post) {
    post.setId(counter.getAndIncrement());
    db.add(post);
    return post;
  }

  public Post update(Post post) {
    var oldPost = getById(post.getId()).orElseThrow(NotFoundException::new);
    removeById(post.getId());
    db.add(post);
    return oldPost;
  }

  public void removeById(long id) {
    db.removeIf(x -> x.getId()==id);
  }
}
