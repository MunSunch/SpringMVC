package ru.netology.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoIn {
    private String message;
    private String author;
}
