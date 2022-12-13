package com.example.boardprac.controller;

import com.example.boardprac.dto.UserRequestDto;
import com.example.boardprac.dto.ResponseDto;
import com.example.boardprac.service.UserService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody UserRequestDto userRequestDto){
        return userService.signup(userRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response){
        return userService.login(userRequestDto,response);
    }


}
