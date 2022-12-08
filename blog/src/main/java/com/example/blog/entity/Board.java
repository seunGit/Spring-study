package com.example.blog.entity;

import com.example.blog.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped{                     // Timestamped는 Board Entity에 상속되어 같이 사용이 됨.

    @Id                                                     // 데이터베이스 테이블의 기본 키(PK)와 객체의 필드를 매핑시켜주는 어노테이션.
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // 기본 키를 자동 생성해주는 어노테이션 (기본 키 생성을 데이터베이스에 위임)
    private Long id;

    @Column(nullable = false)                               // 필드 값
    private String title;
    @Column(nullable = false)                               // 필드 값
    private String content;
    @Column(nullable = false)                               // 필드 값
    private String writer;
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
    }

    public void update(BoardRequestDto responseDto) {
        this.title = responseDto.getTitle();
        this.content = responseDto.getContent();
    }
}
