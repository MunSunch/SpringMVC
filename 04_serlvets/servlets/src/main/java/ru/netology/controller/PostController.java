package ru.netology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.netology.exception.NotFoundException;
import ru.netology.mapping.PostMapper;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class PostController {
  private final PostService service;
  private final PostMapper mapper;

  public PostController(PostService service, PostMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  public void all(HttpServletResponse response) throws IOException {
    final var data = service.all();
    response.getWriter().print(mapper.toJSON(data));
  }

  public void getById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
    try{
      Post post = service.getById(id);
      response.getWriter().print(mapper.toJSON(post));
    } catch (NotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    try {
      final var post = mapper.toPost(readBody(body));
      final var data = service.save(post);
      response.getWriter().print(mapper.toJSON(data));
    } catch (NotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  private String readBody(Reader body) throws IOException {
    int limit = 4096;
    char[] buffer = new char[limit];
    int end = body.read(buffer);
    String result = new String(Arrays.copyOfRange(buffer, 0, end));
    return result;
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    try {
      var removedPost = service.removeById(id);
      response.getWriter().print(mapper.toJSON(removedPost));
    } catch (NotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}
