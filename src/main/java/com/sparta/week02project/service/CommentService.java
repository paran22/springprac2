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
    public List<Comment> getComments(Long boardId) {
        return commentRepository.findAllByBoardIdOrderByCreatedAtDesc(boardId);
    }

    //댓글 저장하기
    public Comment createdComment(CommentDto commentDto, Long userId, String username) {
        Comment comment = new Comment(commentDto, userId, username);
        commentRepository.save(comment);
        return comment;
    }

    //댓글 수정하기
    //1. 댓글 조회하기
    public Comment getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        return comment;
    }

    //2. 댓글 수정하기
    public Comment updateComment(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        comment.setTitle(commentDto.getTitle());
        comment.setContents(commentDto.getContents());
        commentRepository.save(comment);

        return comment;
    }

    //댓글 삭제하기
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
