package com.sparta.week02project.controller;

import com.sparta.week02project.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//페이지 이동 구현
@Controller
public class HomeController {

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

    @GetMapping("/errorpage")
    public String goErrorpage() {
        return "errorpage";
    }
}