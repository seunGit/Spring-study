package com.example.blog.dto;
                                                // 클라이언트에서 리퀘스트로 넘겨주는 Dto

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class BoardRequestDto {              // 변수명 동일.
    private String title;
    private String content;
    private String writer;
    private String password;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

}
