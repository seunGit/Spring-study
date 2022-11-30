package com.example.seungitblog.dto;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardListResponseDto extends ResponseDto{  //상속.

    List<BoardResponseDto> boardList = new ArrayList<>();

    public BoardListResponseDto(){
        super("게시판 목록 조회", HttpStatus.OK.value());
    }

    public void addBoard(BoardResponseDto responseDto) {
        boardList.add(responseDto);
    }
}
