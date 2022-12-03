package com.example.blog.entity;

import com.example.blog.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped{                     //

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private String writer;
    private String password;

//    public Board(BoardRequestDto requestDto) {
//        this.title = title;
//        this.content = content;
//        this.writer = writer;
//        this.password = password;
//    }

    public Board(BoardRequestDto requestDto) {          // 접근제어자 public
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
    }

    public void update(BoardRequestDto responseDto) {
        this.title = responseDto.getTitle();
        this.content = responseDto.getContent();
        this.writer = responseDto.getWriter();
        this.password = responseDto.getPassword();
    }
}
