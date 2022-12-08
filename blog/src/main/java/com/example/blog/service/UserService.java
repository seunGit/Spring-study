package com.example.blog.service;

import com.example.blog.dto.LoginRequestDto;
import com.example.blog.dto.SignupRequestDto;
import com.example.blog.entity.User;
import com.example.blog.jwt.JwtUtil;
import com.example.blog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
//        String email = signupRequestDto.getEmail();
//        // 사용자 ROLE 확인
//        UserRoleEnum role = UserRoleEnum.USER;
//        if (signupRequestDto.isAdmin()) {
//            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }
        User user = new User(username, password);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자없다")
        );

        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸다.");
//            System.out.println("비밀번호 오류");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));

    }
}
