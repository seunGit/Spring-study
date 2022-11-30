package com.example.seungitblog.service;


import com.example.seungitblog.dto.BoardRequestDto;
import com.example.seungitblog.dto.ResponseDto;
import com.example.seungitblog.entity.Board;
import com.example.seungitblog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository; // 저장을 하는 부분
    @Transactional
    public ResponseDto saveBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new ResponseDto();
    }
}
