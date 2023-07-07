package ru.netology.mapping;

import ru.netology.dto.PostDtoIn;
import ru.netology.dto.PostDtoOut;
import ru.netology.model.Post;

import java.util.List;

public interface PostMapper {
    Post toPost(PostDtoIn postDtoIn);
    PostDtoOut toPostDtoOut(Post post);
}
