package ru.netology.mapping;

import ru.netology.model.Post;

import java.util.List;

public interface PostMapper {
    String toJSON(Post post);
    String toJSON(List<Post> posts);
    Post toPost(String json);
}
