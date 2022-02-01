package com.sparta.week02project.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private String title;
    private String contents;
    private Long boardId;
//    private LocalDateTime modifiedAt;
}
