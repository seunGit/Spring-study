package com.example.blog.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseDto {
    private String msg;
    private int statusCode;



    public ResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;

    }
}
