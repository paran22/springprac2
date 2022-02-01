package com.sparta.week02project.controller;

import com.sparta.week02project.dto.BoardDto;
import com.sparta.week02project.dto.CommentDto;
import com.sparta.week02project.entity.Board;
import com.sparta.week02project.entity.Comment;
import com.sparta.week02project.security.UserDetailsImpl;
import com.sparta.week02project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/boards/detail/comment/{boardId}")
    public List<Comment> getComments(@PathVariable Long boardId) {
        List<Comment> commentList = commentService.getComments(boardId);
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

    //댓글 수정하기
    //1. 댓글 조회하기
    @GetMapping("/boards/comment/{id}")
    public Comment getComment(@PathVariable Long id) {
        Comment comment = commentService.getComment(id);
        return comment;
    }

    //2. 댓글 수정하기
    @PutMapping("/boards/comment/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        Comment comment = commentService.updateComment(id, commentDto);
        return comment.getId();
    }

    //댓글 삭제하기
    @DeleteMapping("/boards/comment/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return id;
    }

    //댓글 수정, 삭제 권한 부여를 위해 user값 보내기
    @GetMapping("/logininfo")
    public Long getLoginInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long loginId = userDetails.getUser().getId();
        return loginId;
    }
}
