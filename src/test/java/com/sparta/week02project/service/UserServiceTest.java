package com.sparta.week02project.service;

import com.sparta.week02project.dto.SignupRequestDto;
import com.sparta.week02project.entity.User;
import com.sparta.week02project.repository.UserRepository;
import com.sparta.week02project.validation.SignupValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Nested
    @DisplayName("유효성검사")
    class ValidationTest {
        @Test
        @DisplayName("정상 케이스")
        void signupNormal() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "Admin1",
                    "a0123456789",
                    "a0123456789",
                    "abc@gmail.net"
            );

            //when
            boolean result = SignupValidation.validationSignupInput(requestDto);

            //then
            assertTrue(result);
        }

        @Test
        @DisplayName("실패 케이스_username_한글")
        void signupFail1() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "아이디",
                    "a0123456789",
                    "a0123456789",
                    "abc@gmail.net"
            );

            //when
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                SignupValidation.validationSignupInput(requestDto);
            });

            //then
            assertEquals("username 조건이 맞지 않습니다.", e.getMessage());
        }

        @Test
        @DisplayName("실패 케이스_username_구성")
        void signupFail2() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "11",
                    "a0123456789",
                    "a0123456789",
                    "abc@gmail.net"
            );

            //when
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                SignupValidation.validationSignupInput(requestDto);
            });

            //then
            assertEquals("username 조건이 맞지 않습니다.", e.getMessage());
        }

        @Test
        @DisplayName("실패 케이스_password길이1")
        void signupFail3() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "admin",
                    "aaa",
                    "aaa",
                    "abc@gmail.net"
            );

            //when
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                SignupValidation.validationSignupInput(requestDto);
            });

            //then
            assertEquals("비밀번호는 최소 4자 이상입니다.", e.getMessage());
        }

        @Test
        @DisplayName("실패 케이스_password길이")
        void signupFail4() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "admin",
                    "11",
                    "11",
                    "abc@gmail.net"
            );

            //when
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                SignupValidation.validationSignupInput(requestDto);
            });

            //then
            assertEquals("비밀번호는 최소 4자 이상입니다.", e.getMessage());
        }

        @Test
        @DisplayName("실패 케이스_password_user1")
        void signupFail5() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "admin",
                    "admin",
                    "admin",
                    "abc@gmail.net"
            );

            //when
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                SignupValidation.validationSignupInput(requestDto);
            });

            //then
            assertEquals("비밀번호에 username을 사용할 수 없습니다.", e.getMessage());
        }

        @Test
        @DisplayName("실패 케이스_password_user2")
        void signupFail6() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "Admin123",
                    "Admin12345",
                    "Admin12345",
                    "abc@gmail.net"
            );

            //when
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                SignupValidation.validationSignupInput(requestDto);
            });

            //then
            assertEquals("비밀번호에 username을 사용할 수 없습니다.", e.getMessage());
        }

        @Test
        @DisplayName("실패 케이스_password일치1")
        void signupFail7() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "Admin1",
                    "a0248",
                    "aaa",
                    "abc@gmail.net"
            );

            //when
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                SignupValidation.validationSignupInput(requestDto);
            });

            //then
            assertEquals("비밀번호가 일치하지 않습니다.", e.getMessage());
        }

        @Test
        @DisplayName("실패 케이스_password일치2")
        void signupFail8() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "Admin1",
                    "0248",
                    "024",
                    "abc@gmail.net"
            );

            //when
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                SignupValidation.validationSignupInput(requestDto);
            });

            //then
            assertEquals("비밀번호가 일치하지 않습니다.", e.getMessage());
        }


    }

    @Nested
    @DisplayName("username 중복검사")
    class DubCheckUsername {

        @InjectMocks
        private UserService userService;

        @Mock
        private UserRepository userRepository;

        @Test
        @DisplayName("중복1")
        void checkUsername1() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "admin1",
                    "a0123456789",
                    "a0123456789",
                    "abc@gmail.net"
            );

            given(userRepository.existsByUsername(any())).willReturn(true);


            // when
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                userService.registerUser(requestDto);
            });

            // then
            assertEquals("중복된 username 입니다.", exception.getMessage());
        }

        @Test
        @DisplayName("중복2")
        void checkUsername2() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    "piano2",
                    "a0123456789",
                    "a0123456789",
                    "abc@gmail.net"
            );

            given(userRepository.existsByUsername(any())).willReturn(true);


            // when
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                userService.registerUser(requestDto);
            });

            // then
            assertEquals("중복된 username 입니다.", exception.getMessage());
        }

    }
}
