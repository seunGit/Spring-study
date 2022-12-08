package com.example.blog.controller;

import com.example.blog.dto.BoardListResponseDto;
import com.example.blog.dto.BoardRequestDto;
import com.example.blog.dto.BoardResponseDto;
import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Board;
import com.example.blog.repository.BoardRepository;
import com.example.blog.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController                     // json 형식으로 받기 위함
@RequiredArgsConstructor            // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해준다.
@RequestMapping("/api")          // 공통적인 url이 겹칠때 사용.
public class BoardController {

    private final BoardService boardService;            // db연결
    private final BoardRepository boardRepository;

    @PostMapping("/save/board")                     // 클라이언트로 부터 요청을 받음.                        // 클래스 타입 선언 가능
    public Board saveBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {                     // public 접근제한자 ResponseDto 리턴타입 Body값들을 들고 오겠다고 선언
//        ResponseDto responseDto = boardService.saveBoard(requestDto);                           // 컨트롤러에서 서비스를 호출, 전달.
//        return responseDto;                                                                     // 클라이언트에게 값 전달.
        return boardService.saveBoard(requestDto, request);              // Service에서 받아 Client로 전달.
    }

    @GetMapping("/get/boards")
    public BoardListResponseDto getBoards() {
        return boardService.getBoards();                        // Service에서 받아 Client로 전달.
    }

    @GetMapping("/get/board")
    public BoardResponseDto getBoard(@RequestParam Long id) {
        return boardService.getBoard(id);                       // Service에서 받아 Client로 전달.
    }

    @PutMapping("/update/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);        // Service에서 받아 Client로 전달.
    }

    @DeleteMapping("/delete/board/{id}")
    public ResponseDto deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request){
        return boardService.deleteBoard(id, boardRequestDto, request);                    // Service에서 받아 Client로 전달.
    }
}
