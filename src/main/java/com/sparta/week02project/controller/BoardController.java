package com.sparta.week02project.controller;

import com.sparta.week02project.dto.BoardDto;
import com.sparta.week02project.entity.Board;
import com.sparta.week02project.security.UserDetailsImpl;
import com.sparta.week02project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //전체 게시글 목록보기
    @GetMapping("/boards")
    public List<Board> getBoardList() {
        List<Board> boardList = boardService.getBoardList();
        return boardList;
    }


    //게시글 등록하기
    //아이디 함께 저장하기
    @PostMapping("/write")
    public Board createBoard(@RequestBody BoardDto boardDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        String username = userDetails.getUser().getUsername();
        Board board = boardService.createdBoard(boardDto, userId, username);
        return board;
    }

    //게시글 조회하기
    @GetMapping("/boards/detail/{id}")
    public Board getBoard(@PathVariable Long id){
        Board board = boardService.getBoard(id);
        return board;
    }


}
