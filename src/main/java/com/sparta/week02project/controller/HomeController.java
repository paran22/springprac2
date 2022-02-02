package com.sparta.week02project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//페이지 이동 구현
@Controller
public class HomeController {

    // 회원 로그인 페이지 호출
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지 호출
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    //기본페이지 호출
    @GetMapping("/")
    public String goHome() {
        return "boards";
    }

    //게시글 작성 페이지 호출
    @GetMapping("/write")
    public String goWrite() {
        return "write";
    }

    //게시글 조회하기 페이지 호출
    @GetMapping("/boards/detail")
    public String goDetail() {
        return "detail";
    }

    //에러페이지 호출
    @GetMapping("/errorpage")
    public String goErrorpage() {
        return "errorpage";
    }


}