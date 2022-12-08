package com.example.blog.service;


import com.example.blog.dto.BoardListResponseDto;
import com.example.blog.dto.BoardRequestDto;
import com.example.blog.dto.BoardResponseDto;
import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Board;
import com.example.blog.entity.User;
import com.example.blog.jwt.JwtUtil;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor                                                        // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해준다.

public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;                  // db연결
//    @Transactional  // 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다
//    public ResponseDto saveBoard(BoardRequestDto requestDto, HttpServletRequest request) {                  // 컨트롤러에서 넘어온 Dto 받아줌.
//        Board board = new Board(requestDto);                                    // Board생성자 호출, 객체 초기화, row 생성, entitiy로 보내줌. 데이터베이스에 값을 저장할때는 엔티티를 이용해야하기 때문에,
//                                                                                // DTO에 있는 값을 엔티티로 옮겨주는 과정이다. 옮겨줄때는 매개변수가 있는 생성자를 이용하고있다.
//        boardRepository.save(board);                                            // 데이터베이스의 값을 저장.                   // 매개변수가 있는 생성자와 기본생성자의 차이를 구분하자. 생성자가 뭔지.
//        return new ResponseDto("글 등록 성공2!", HttpStatus.OK.value());   // 클라이언트에게 정보 전달.  컨트롤러로 전달.
//    }
    @Transactional
    public BoardResponseDto saveBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        //Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //토큰이 있는 경우에만 게시글 작성 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                //토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            //토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            //요청 받은 Dto로 DB에 저장할 객체 만들기
            Board board = boardRepository.saveAndFlush(new Board(requestDto, user.getId(), user.getUsername()));

            return new BoardResponseDto(board);
        } else {
            return null;
        }
    }
    @Transactional(readOnly = true)                                             // 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다 (읽기 전용 : 엔티티의 등록, 수정, 삭제가 동작하지 않는다. 성능 최적화에도 도움.)
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
    public ResponseDto deleteBoard(Long id, HttpServletRequest request) {
        //Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //토큰이 있는 경우에만 게시글 삭제 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                //토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            //토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Board board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );

            boardRepository.deleteById(id);

            return new ResponseDto("게시글 삭제 성공", HttpStatus.OK.value());

        } else {
            return null;
        }
    }

    private Board checkBoard(BoardRepository boardRepository, Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("글을 찾을수 없습니다.")
        );
    }

}