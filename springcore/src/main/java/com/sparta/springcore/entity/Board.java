package com.sparta.springcore.entity;

import com.sparta.springcore.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import util.Timestamped;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor  // 기본 생성자를 만든다
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려준다.
public class Board extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Board(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.username = boardDto.getUsername();
        this.password = boardDto.getPassword();
    }

    public boolean update(BoardDto boardDto) {
        if (!Objects.equals(this.password, boardDto.getPassword())) {
            return false;
        } else {
            this.title = boardDto.getTitle();
            this.content = boardDto.getContent();
            return true;
        }
    }
}
