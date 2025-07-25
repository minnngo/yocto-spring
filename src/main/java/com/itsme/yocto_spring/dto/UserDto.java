package com.itsme.yocto_spring.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String name;
    private String phoneNumber;
    private String password;
    private String level;
}
