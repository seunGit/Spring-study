package com.example.seungitblog.service;


import com.example.seungitblog.dto.BoardListResponseDto;
import com.example.seungitblog.dto.BoardRequestDto;
import com.example.seungitblog.dto.BoardResponseDto;
import com.example.seungitblog.dto.ResponseDto;
import com.example.seungitblog.entity.Board;
import com.example.seungitblog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository; // 저장을 하는 부분
    @Transactional
    public ResponseDto saveBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);        // 저장 row 한줄을 만들었다.
        boardRepository.save(board);
        return new ResponseDto("등록이 되었습니다.", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public BoardListResponseDto getBoards() {
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();

        List<Board> boards = boardRepository.findAll();

        for (Board board : boards) {
            boardListResponseDto.addBoard(new BoardResponseDto(board));
        }
        return boardListResponseDto;
    }
    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("글을 찾을수 없다.")
        );
        return new BoardResponseDto(board);
    }
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () ->  new RuntimeException("글을 찾을수 없다.")
        );

        board.update(requestDto);

        return new BoardResponseDto(board);

    }

    public ResponseDto deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () ->  new RuntimeException("글을 찾을수 없다.")
        );
        boardRepository.delete(board);
        return new ResponseDto("글 삭제 성공", HttpStatus.OK.value());
    }
}



