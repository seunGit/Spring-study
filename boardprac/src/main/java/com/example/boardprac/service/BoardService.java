package com.example.boardprac.service;


import com.example.boardprac.dto.BoardListResponseDto;
import com.example.boardprac.dto.BoardRequestDto;
import com.example.boardprac.dto.BoardResponseDto;
import com.example.boardprac.dto.ResponseDto;
import com.example.boardprac.entity.Board;
import com.example.boardprac.entity.User;
import com.example.boardprac.jwt.JwtUtil;
import com.example.boardprac.repository.BoardRepository;
import com.example.boardprac.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    //BoardRepository 연결되어 사용할 수 있다.
    private final JwtUtil jwtUtil;

    @Transactional
    public BoardResponseDto boardWrite(BoardRequestDto boardRequestDto, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        // JWT 안에 있는 정보들을 담을 수 있는 객체
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
                // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회 - JwtUtil 에서 토큰 생성시 setSubject(username) 했기 때문에 getSubject()
                User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                        () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
                );

                //로그인한 username 으로 글작성
                Board board = new Board(boardRequestDto, user); //db에 저장할 데이터 1 row 을 만들었다 라고 이해
                boardRepository.save(board);

                return new BoardResponseDto(board);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

    }

    @Transactional(readOnly = true)
    //데이터 추가, 수정, 삭제 등으로 이루어진 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다.
    public BoardListResponseDto getBoardList() {
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();

        List<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc(); //작성일 기준 내림차순

        for (Board board : boardList) {
            boardListResponseDto.addBoardList(new BoardResponseDto(board));
        }
        return boardListResponseDto;
    }

    @Transactional //이거 안쓰면 db에 저장 안됨!!!! 필수임
    public BoardResponseDto getBoardDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto boardUpdate(Long id, BoardRequestDto boardRequestDto, HttpServletRequest request) {
        //1. 글 유무확인
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );

        //2. 토큰 유무 확인 : Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        // JWT 안에 있는 정보들을 담을 수 있는 객체
        Claims claims;

        if (token != null) {
            //Token 검증 (위변조)
            if (jwtUtil.validateToken(token)){
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

                //글작성자와 로그인한 사용자 일치 유무 확인
                if (claims.getSubject().equals(board.getUser().getUsername())) {

                    board.update(boardRequestDto); //boardRepository와 연결한게 없는데 어케 update 되는건지?

                } else {
                    throw new IllegalArgumentException("글 작성자만 수정 가능합니다.");
                }

                return new BoardResponseDto(board);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

        } else {
            throw new IllegalArgumentException("토큰이 없습니다.");
        }
    }

    @Transactional
    public ResponseDto boardDelete(Long id, HttpServletRequest request) {
        //1. 글 유무확인
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );

        //2. 토큰 유무 확인 : Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        // JWT 안에 있는 정보들을 담을 수 있는 객체
        Claims claims;

        if (token != null) {
            //Token 검증 (위변조)
            if (jwtUtil.validateToken(token)){
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

                //글작성자와 로그인한 사용자 일치 유무 확인
                if (claims.getSubject().equals(board.getUser().getUsername())) {

                    boardRepository.deleteById(id);
                    return new ResponseDto("글삭제 완료", HttpStatus.OK.value());

                } else {
                    throw new IllegalArgumentException("글 작성자만 삭제 가능합니다.");
                }

            } else {
                throw new IllegalArgumentException("Token Error");
            }

        } else {
            throw new IllegalArgumentException("토큰이 없습니다.");
        }

    }
}
