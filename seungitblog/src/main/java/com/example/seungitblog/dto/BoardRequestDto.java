package com.example.seungitblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class BoardRequestDto {

    private String title;
    private String content;
    private Long password;

}
