package com.itsme.yocto_spring.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    private String phoneNumber;
    private String password;
}
