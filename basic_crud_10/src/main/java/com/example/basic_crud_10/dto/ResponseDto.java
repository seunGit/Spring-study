package com.example.basic_crud_10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter                         // 받음
@NoArgsConstructor              //
public class ResponseDto {
    private String msg;         // response 하는 목록 구성
    private int statusCode;

    public ResponseDto(String msg, int statusCode) { // 생성자 셋팅. @Getter, @Setter 사용해도 됨
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
