package ru.netology.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
  private long id;
  private String content;
  private String author;
  private Status status;
}
