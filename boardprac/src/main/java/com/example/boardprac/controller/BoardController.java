package com.example.boardprac.controller;

        import com.example.boardprac.dto.BoardListResponseDto;
        import com.example.boardprac.dto.BoardRequestDto;
        import com.example.boardprac.dto.BoardResponseDto;
        import com.example.boardprac.dto.ResponseDto;
        import com.example.boardprac.service.BoardService;
        import lombok.RequiredArgsConstructor;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardService;

    //게시글 작성
    @PostMapping("/write")
    public BoardResponseDto boardWrite(@RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {
        return boardService.boardWrite(boardRequestDto,request);
    }

    //게시물 전체 조회
    @GetMapping("/list")
    public BoardListResponseDto getBoardList() {
        return boardService.getBoardList();
    }

    //게시물 상세 조회
    @GetMapping("/detail")
    public BoardResponseDto getBoardDetail(@RequestParam Long id) {
        return boardService.getBoardDetail(id);
    }

    //게시물 수정
    @PutMapping("/update/{id}")
    public BoardResponseDto boardUpdate(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {
        return boardService.boardUpdate(id, boardRequestDto, request);
    }
    //게시물 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseDto boardDelete(@PathVariable Long id, HttpServletRequest request) {
        return boardService.boardDelete(id,request);
    }



}