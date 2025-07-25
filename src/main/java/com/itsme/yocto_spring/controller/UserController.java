package com.itsme.yocto_spring.controller;

import com.itsme.yocto_spring.dto.LoginRequestDto;
import com.itsme.yocto_spring.dto.LoginResponseDto;
import com.itsme.yocto_spring.dto.UserDto;
import com.itsme.yocto_spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto request) {
        return ResponseEntity.status(201).body(userService.signup(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
