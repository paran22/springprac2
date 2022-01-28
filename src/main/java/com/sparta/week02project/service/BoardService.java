package com.sparta.week02project.service;

import com.sparta.week02project.dto.BoardDto;
import com.sparta.week02project.entity.Board;
import com.sparta.week02project.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoardList() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    public Board createdBoard(BoardDto boardDto, Long userId, String username) {
        Board board = new Board(boardDto, userId, username);
        boardRepository.save(board);
        return board;
    }

}
