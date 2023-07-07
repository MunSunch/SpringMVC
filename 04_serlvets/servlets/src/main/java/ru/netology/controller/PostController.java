package ru.netology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.netology.dto.PostDtoIn;
import ru.netology.dto.PostDtoOut;
import ru.netology.exception.NotFoundException;
import ru.netology.mapping.PostMapper;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
  private final PostService service;
  private final PostMapper mapper;

  @Autowired
  public PostController(PostService service, PostMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping("/all")
  public List<PostDtoOut> allActive() throws IOException {
    return service.allActive();
  }

  @GetMapping("/get/{id}")
  public PostDtoOut getById(@PathVariable(name="id") long id) {
    return service.getById(id);
  }

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.CREATED)
  public PostDtoOut save(@RequestBody PostDtoIn post) throws IOException {
    post.setAuthor(Thread.currentThread().getName());
    return service.save(post);
  }

  @PutMapping("/update/{id}")
  public PostDtoOut update(@PathVariable(name="id") long id, @RequestBody PostDtoIn post) {
    post.setAuthor(Thread.currentThread().getName());
    return service.update(id, post);
  }

  @DeleteMapping("/remove/{id}")
  public PostDtoOut removeById(@PathVariable(name="id") long id) throws IOException {
    return service.removeById(id);
  }
}
