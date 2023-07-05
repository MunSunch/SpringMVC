package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;
import ru.netology.mapping.PostMapper;
import ru.netology.mapping.impl.PostMapperImpl;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

@Configuration
public class Config {
    @Bean
    public PostController postController() {
        return new PostController(postService(), postMapper());
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }

    @Bean
    public PostMapper postMapper() {
        return new PostMapperImpl();
    }
}
