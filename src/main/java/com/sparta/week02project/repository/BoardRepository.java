package com.sparta.week02project.repository;

import com.sparta.week02project.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //최신순으로 정렬
    List<Board> findAllByOrderByCreatedAtDesc();
}
