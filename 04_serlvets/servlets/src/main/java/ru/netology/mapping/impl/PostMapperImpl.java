package ru.netology.mapping.impl;

import com.google.gson.Gson;
import ru.netology.mapping.PostMapper;
import ru.netology.model.Post;

import java.util.List;

public class PostMapperImpl implements PostMapper {
    private final Gson gson;

    public PostMapperImpl() {
        this.gson = new Gson();
    }

    @Override
    public String toJSON(Post post) {
        return gson.toJson(post);
    }

    @Override
    public Post toPost(String json) {
        return gson.fromJson(json, Post.class);
    }

    @Override
    public String toJSON(List<Post> posts) {
        return gson.toJson(posts);
    }
}
