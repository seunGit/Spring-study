package com.example.blog.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardListResponseDto extends ResponseDto{              //ResponseDto에서 상속받아서 사용한다.

    List<BoardResponseDto> boardList = new ArrayList<>();            //List 형식 초기화

    public BoardListResponseDto() {
        super("블로그 게시물 목록 조회 성공", HttpStatus.OK.value());
    }

    public void addBoard(BoardResponseDto responseDto) {            //boardList에 넣어줄 값이 필요하다. 매개변수
        boardList.add(responseDto);                                 // 추가
    }

}
