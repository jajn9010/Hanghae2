package com.sparta.expert_mission.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequestDto {
    private Long userId;
    private String title;
    private String username;
    private String contents;
}
