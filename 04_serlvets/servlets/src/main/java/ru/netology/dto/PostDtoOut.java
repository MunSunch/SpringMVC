package ru.netology.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoOut {
    private long id;
    private String message;
    private String author;
}
