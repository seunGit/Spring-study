package com.example.blog.dto;


import com.example.blog.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class BoardResponseDto {
    private String title;
    private String content;
    private String writer;
    private String password;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.writer = board.getWriter();
        this.content = board.getContent();
        this.password = board.getPassword();
        this.modifiedAt = board.getModifiedAt();
        this.createdAt = board.getCreatedAt();
    }
}
