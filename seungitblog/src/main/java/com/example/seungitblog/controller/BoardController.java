package com.example.seungitblog.controller;

import com.example.seungitblog.dto.BoardListResponseDto;
import com.example.seungitblog.dto.BoardRequestDto;
import com.example.seungitblog.dto.BoardResponseDto;
import com.example.seungitblog.dto.ResponseDto;
import com.example.seungitblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;            // boardservice 와 연결 상태 확인

    @PostMapping("/save/board")
    public ResponseDto saveBoard(@RequestBody BoardRequestDto requestDto) {
            return boardService.saveBoard(requestDto);
    }

    @GetMapping("/get/boards")
    public BoardListResponseDto getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("/get/board/")
    public BoardResponseDto getBoard(@RequestParam Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/update/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id,@RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id,requestDto);
    }

    @DeleteMapping("/delete/board/{id}")
    public ResponseDto deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }
}
