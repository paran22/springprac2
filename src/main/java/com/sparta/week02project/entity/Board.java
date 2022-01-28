package com.sparta.week02project.entity;

import com.sparta.week02project.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;

    public Board(BoardDto boardDto, Long userId, String username) {
        this.userId = userId;
        this.username = username;
        this.title = boardDto.getTitle();
        this.contents = boardDto.getContents();
    }

}