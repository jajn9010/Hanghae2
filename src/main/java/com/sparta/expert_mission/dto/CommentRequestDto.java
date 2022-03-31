package com.sparta.expert_mission.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private String user;
    private String text;
    private Long blogId;
}
