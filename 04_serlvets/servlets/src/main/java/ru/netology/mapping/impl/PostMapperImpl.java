package ru.netology.mapping.impl;

import org.springframework.stereotype.Component;
import ru.netology.dto.PostDtoIn;
import ru.netology.dto.PostDtoOut;
import ru.netology.mapping.PostMapper;
import ru.netology.model.Post;
import ru.netology.model.Status;

@Component
public class PostMapperImpl implements PostMapper {
    @Override
    public Post toPost(PostDtoIn postDtoIn) {
        Post post = new Post();
            post.setContent(postDtoIn.getMessage());
            post.setAuthor(postDtoIn.getAuthor());
        return post;
    }

    @Override
    public PostDtoOut toPostDtoOut(Post post) {
        PostDtoOut postDtoOut = new PostDtoOut();
            postDtoOut.setId(post.getId());
            postDtoOut.setMessage(post.getContent());
            postDtoOut.setAuthor(post.getAuthor());
        return postDtoOut;
    }
}
