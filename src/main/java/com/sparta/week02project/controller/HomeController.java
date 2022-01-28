package com.sparta.week02project.controller;

import com.sparta.week02project.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //기본페이지 호출
    @GetMapping("/")
    public String home() {
        return "boards";
    }

    //전체목록페이지로 이동하도록 수정 필요
    @GetMapping("/boardslogin")
    public String homeLogin(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "boardslogin";
    }
}