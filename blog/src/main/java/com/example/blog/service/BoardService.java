package com.example.blog.service;


import com.example.blog.dto.BoardListResponseDto;
import com.example.blog.dto.BoardRequestDto;
import com.example.blog.dto.BoardResponseDto;
import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Board;
import com.example.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service                // 서비스 탭에서 사용되는 어노테이션.
@RequiredArgsConstructor

public class BoardService {

    private final BoardRepository boardRepository;
    @Transactional
    public ResponseDto saveBoard(BoardRequestDto requestDto) {                          // 컨트롤러에서 넘어온 Dto 받아줌.
        Board board = new Board(requestDto);       //Board생성자 호출, 객체 초기화        // row 생성, entitiy로 보내줌. 데이터베이스에 값을 저장할때는 엔티티를 이용해야하기 때문에,
                                                                                // DTO에 있는 값을 엔티티로 옮겨주는 과정이다. 옮겨줄때는 매개변수가 있는 생성자를 이용하고있다.
        boardRepository.save(board);                                            // 데이터베이스의 값을 저장.                   // 매개변수가 있는 생성자와 기본생성자의 차이를 구분하자. 생성자가 뭔지.
        return new ResponseDto("글 등록 성공2!", HttpStatus.OK.value());   // 클라이언트에게 정보 전달.  컨트롤러로 전달.
    }

    @Transactional(readOnly = true)
    public BoardListResponseDto getBoards() {
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();
        List<Board> boards = boardRepository.findAll();                 // 값
        for (Board board : boards) {
            boardListResponseDto.addBoard(new BoardResponseDto(board));
        }
        return boardListResponseDto;
    }


    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("글을 찾을수 없습니다.")
        );
        return new BoardResponseDto(board);
    }


    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto responseDto) {
        Board board = checkBoard(boardRepository, id);
        board.update(responseDto);
        return new BoardResponseDto(board);
    }

    @Transactional
    public ResponseDto deleteBoard(Long id) {
        Board board = checkBoard(boardRepository, id);
        boardRepository.delete(board);
        return new ResponseDto("글 삭제 성공", HttpStatus.OK.value());
    }

    private Board checkBoard(BoardRepository boardRepository, Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("글을 찾을수 없습니다.")
        );
    }

}