package com.example.seungitblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseDto {
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String id;
    private String title;
    private String content;
    private String msg;

}
