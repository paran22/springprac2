package com.sparta.week02project.service;


import com.sparta.week02project.dto.SignupRequestDto;
import com.sparta.week02project.entity.User;
import com.sparta.week02project.repository.UserRepository;
import com.sparta.week02project.validation.SignupValidation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SignupValidation signupValidation;

//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public void registerUser(SignupRequestDto requestDto) {
        if (SignupValidation.validationSignupInput(requestDto)) {
            String username = requestDto.getUsername();
            if (userRepository.existsByUsername(username)) {
                throw new IllegalArgumentException("중복된 username 입니다.");
            }
            // 패스워드 암호화
            String password = passwordEncoder.encode(requestDto.getPassword());
            String email = requestDto.getEmail();

            User user = new User(username, password, email);
            userRepository.save(user);
        }
    }
    //username 중복확인
    public boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}