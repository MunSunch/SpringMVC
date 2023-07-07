package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
  private final ConcurrentHashMap<Long, Post> db;
  private static AtomicLong counter;

  public PostRepository() {
    counter = new AtomicLong(1);
    db = new ConcurrentHashMap<>();
      db.put(counter.incrementAndGet(), new Post(counter.get(), "post#1", "MunSun", Status.ACTIVE));
      db.put(counter.incrementAndGet(), new Post(counter.get(), "post#2", "MunSun", Status.ACTIVE));
      db.put(counter.incrementAndGet(), new Post(counter.get(), "post#3", "MunSun", Status.ACTIVE));
  }

  public List<Post> all() {
    return new ArrayList<>(db.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(db.get(id));
  }

  public Post save(Post post) {
    post.setId(counter.incrementAndGet());
    post.setStatus(Status.ACTIVE);
    db.put(counter.get(), post);
    return post;
  }

  public Post update(Post post) {
    var oldPost = getById(post.getId()).orElseThrow(NotFoundException::new);
    db.replace(post.getId(), oldPost, post);
    return post;
  }

  public Post removeById(long id) {
    return db.remove(id);
  }
}
