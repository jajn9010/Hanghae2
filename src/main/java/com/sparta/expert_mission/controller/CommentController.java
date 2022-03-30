package com.sparta.expert_mission.controller;

import com.sparta.expert_mission.dto.CommentRequestDto;
import com.sparta.expert_mission.model.Comment;
import com.sparta.expert_mission.repository.CommentRepository;
import com.sparta.expert_mission.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    @PostMapping("/api/comment")
    public Comment createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto){
        Comment comment = new Comment(requestDto, userDetails.getUser().getUsername());

        return commentRepository.save(comment);
    }
}