package com.example.blog.dto;


import com.example.blog.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class BoardResponseDto {
    private String title;
    private String content;
    private String writer;
    private Long id;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

//    public BoardResponseDto(String title, String content, String writer, String password, LocalDateTime modifiedAt, LocalDateTime createdAt) {
//        this.title = title;
//        this.content = content;
//        this.writer = writer;
//        this.password = password;
//        this.modifiedAt = modifiedAt;
//        this.createdAt = createdAt;
//    }

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.writer = board.getWriter();
        this.content = board.getContent();
        this.modifiedAt = board.getModifiedAt();
        this.createdAt = board.getCreatedAt();
    }
}
