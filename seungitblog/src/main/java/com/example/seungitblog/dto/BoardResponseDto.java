package com.example.seungitblog.dto;


import com.example.seungitblog.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String id;
    private String title;
    private String content;

    public BoardResponseDto(Board board) {

        this.content = board.getContent();
        this.title = board.getTitle();
    }
}
