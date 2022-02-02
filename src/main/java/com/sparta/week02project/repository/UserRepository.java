package com.sparta.week02project.repository;

import com.sparta.week02project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByKakaoId(Long kakaoId);

    //username 중복확인
    boolean existsByUsername(String username);
}