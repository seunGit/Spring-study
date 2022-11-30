package com.example.seungitblog.controller;

import com.example.seungitblog.dto.BoardRequestDto;
import com.example.seungitblog.dto.ResponseDto;
import com.example.seungitblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;            // boardservice 와 연결 상태 확인

    @PostMapping("/board")
    public ResponseDto saveboard(@RequestBody BoardRequestDto requestDto) {
            return boardService.saveBoard(requestDto);
    }
}
