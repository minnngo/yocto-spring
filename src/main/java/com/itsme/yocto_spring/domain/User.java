package com.itsme.yocto_spring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String userId;  // UUID 직접 할당

    private String name;

    @Column(unique = true)
    private String phoneNumber;

    private String password;

    private String level;  // Beginner, Intermediate 등
}
