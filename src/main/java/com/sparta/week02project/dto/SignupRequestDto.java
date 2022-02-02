package com.sparta.week02project.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;

@Setter
@Getter
public class SignupRequestDto {
//    @NotBlank(message = "username을 입력해주세요.")
//    @Pattern(regexp = "^[a-zA-Z0-9]{3,10}$", message = "username은 영어 대/소문자와 숫자로 3~10자로 입력해주세요.")
    private String username;

    //동작 제대로 안함
//    @Min(value = 4, message = "password는 4자 이상 입력해주세요.")
    private String password;

//    @NotBlank(message = "이메일을 입력해주세요.")
//    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;
}
