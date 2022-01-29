package com.sparta.week02project.controller;

import com.sparta.week02project.dto.BoardDto;
import com.sparta.week02project.dto.CommentDto;
import com.sparta.week02project.entity.Board;
import com.sparta.week02project.entity.Comment;
import com.sparta.week02project.security.UserDetailsImpl;
import com.sparta.week02project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 조회하기
    @GetMapping("/boards/detail/comment/{userId}")
    public List<Comment> getComment(@PathVariable Long userId) {
        List<Comment> commentList = commentService.getComment(userId);
        return commentList;
    }

    //댓글 입력하기
    @PostMapping("/boards/detail")
    public Comment createComment(@RequestBody CommentDto commentDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        String username = userDetails.getUser().getUsername();
        Comment comment = commentService.createdComment(commentDto, userId, username);
        return comment;
    }
}
