package com.sparta.week02project.service;

import com.sparta.week02project.dto.CommentDto;
import com.sparta.week02project.entity.Comment;
import com.sparta.week02project.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //댓글 조회하기
    public List<Comment> getComment(Long userId) {
        return commentRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
    }

    //댓글 저장하기
    public Comment createdComment(CommentDto commentDto, Long userId, String username) {
        Comment comment = new Comment(commentDto, userId, username);
        commentRepository.save(comment);
        return comment;
    }
}
