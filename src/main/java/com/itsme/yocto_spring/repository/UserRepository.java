package com.itsme.yocto_spring.repository;

import com.itsme.yocto_spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findFirstByPhoneNumber(String phoneNumber);
}
