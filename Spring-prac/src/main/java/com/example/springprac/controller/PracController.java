package com.example.springprac.controller;


import com.example.springprac.dto.BoardRequestDto;
import com.example.springprac.dto.ResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PracController {

    @PostMapping("/save/board")
    public ResponseDto saveBoard(@RequestBody BoardRequestDto requestDto) {
        return boardservice.saveBoard(requestDto);
    }


}
