package com.sparta.week02project.entity;

import com.sparta.week02project.dto.BoardDto;
import com.sparta.week02project.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {
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

    public Comment(CommentDto commentDto, Long userId, String username) {
        this.userId = userId;
        this.username = username;
        this.title = commentDto.getTitle();
        this.contents = commentDto.getContents();
    }

}
