package com.example.springprac.service;


import com.example.springprac.dto.BoardRequestDto;
import com.example.springprac.dto.ResponseDto;
import com.example.springprac.entity.Board;
import com.example.springprac.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BoardService {

    private final BoardRepository saveBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);

        boardRepository.save(board);
        return new ResponseDto("글 등록 성공3", HttpStatus.OK.value());
    }
}
