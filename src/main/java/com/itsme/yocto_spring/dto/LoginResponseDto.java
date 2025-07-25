package com.itsme.yocto_spring.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private String userId;
    private String name;
    private String level;
}
