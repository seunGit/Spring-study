package com.example.boardprac.service;

import com.example.boardprac.dto.ResponseDto;
import com.example.boardprac.dto.UserRequestDto;
import com.example.boardprac.entity.User;
import com.example.boardprac.jwt.JwtUtil;
import com.example.boardprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    @Transactional
    public ResponseDto signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        Optional<User> usernameCheck = userRepository.findByUsername(username);

        if (usernameCheck.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);

        return new ResponseDto("회원가입 되었습니다.", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public ResponseDto login(UserRequestDto userRequestDto, HttpServletResponse response) {
        // 사용자 확인
        User user = userRepository.findByUsername(userRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        // 비밀번호 확인
        if (!user.getPassword().equals(userRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //토큰 생성
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));

        return new ResponseDto("로그인 완료!!.", HttpStatus.OK.value());
    }
}
