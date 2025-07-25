package com.itsme.yocto_spring.service;

import com.itsme.yocto_spring.domain.User;
import com.itsme.yocto_spring.dto.LoginRequestDto;
import com.itsme.yocto_spring.dto.LoginResponseDto;
import com.itsme.yocto_spring.dto.UserDto;
import com.itsme.yocto_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public UserDto signup(UserDto dto) {
        // 동일한 phoneNumber가 이미 존재하는지 검사 (옵션)
        Optional<User> existingUser = userRepository.findFirstByPhoneNumber(dto.getPhoneNumber());
        if (existingUser.isPresent()) {
            throw new RuntimeException("이미 등록된 전화번호입니다.");
        }

        // UUID를 직접 생성
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .password(dto.getPassword())
                .level("Beginner")
                .build();

        userRepository.save(user);

        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .level(user.getLevel())
                .build();
    }

    // 로그인
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findFirstByPhoneNumber(dto.getPhoneNumber())
                .filter(u -> u.getPassword().equals(dto.getPassword()))
                .orElseThrow(() -> new RuntimeException("잘못된 로그인 정보입니다."));

        return LoginResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .level(user.getLevel())
                .build();
    }
}
