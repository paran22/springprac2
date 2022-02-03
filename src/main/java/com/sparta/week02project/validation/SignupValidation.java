package com.sparta.week02project.validation;

import com.sparta.week02project.dto.SignupRequestDto;
import com.sparta.week02project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class SignupValidation {

    private final UserRepository userRepository;

    public static boolean validationSignupInput(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String passwordConfirm = signupRequestDto.getPasswordConfirm();
        //username 확인
        if (!username.matches("^[a-zA-Z0-9]{3,15}$")) {
            throw new IllegalArgumentException("username 조건이 맞지 않습니다.");
        }

        //password 확인
        if (password.length() < 4) {
            throw new IllegalArgumentException("비밀번호는 최소 4자 이상입니다.");
        }

        if (password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에 username을 사용할 수 없습니다.");
        }

        //passwordConfirm 확인
        if (!password.equals(passwordConfirm)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return true;
    }
}
