package com.sparta.expert_mission.model;

import com.sparta.expert_mission.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Long blogId;

    @Column(nullable = false)
    private String user;

    public Comment(CommentRequestDto requestDto, String username){
        this.text = requestDto.getText();
        this.user = username;
        this.blogId = requestDto.getBlogId();
    }

    public void updateComment (CommentRequestDto requestDto) {
        this.text = requestDto.getText();
    }
}
