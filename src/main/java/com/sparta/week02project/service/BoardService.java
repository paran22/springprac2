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

    //전체조회하기(최신순 정렬)
    public List<Board> getBoardList() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    //게시글 저장하기
    public Board createdBoard(BoardDto boardDto, Long userId, String username) {
        Board board = new Board(boardDto, userId, username);
        boardRepository.save(board);
        return board;
    }

    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).
                orElseThrow(() -> new NullPointerException("아이디가 존재하지 않습니다."));
        return board;
    }
}
