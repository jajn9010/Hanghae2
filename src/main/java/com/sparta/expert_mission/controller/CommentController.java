package com.sparta.expert_mission.controller;

import com.sparta.expert_mission.dto.CommentRequestDto;
import com.sparta.expert_mission.model.Comment;
import com.sparta.expert_mission.repository.CommentRepository;
import com.sparta.expert_mission.security.UserDetailsImpl;
import com.sparta.expert_mission.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public Comment createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto){
        Comment comment = new Comment(requestDto, userDetails.getUser().getUsername());

        return commentRepository.save(comment);
    }

    @GetMapping("/api/comment/{id}")
    public List<Comment> getComments(@PathVariable Long id) {
        for(Comment comment:commentRepository.findAllByBlogIdOrderByModifiedAt(id)){
            System.out.println(comment);
        }
        System.out.println(commentRepository.findAllByBlogIdOrderByModifiedAt(id));
        return commentRepository.findAllByBlogIdOrderByModifiedAt(id);
    }

    @PutMapping("/api/comment/{id}")
    public String submitComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.updateComment(requestDto, id, userDetails.getUser().getUsername());
        return "댓글이 성공적으로 수정되었습니다.";
    }

    @DeleteMapping("/api/comment/{id}")
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(id, userDetails.getUser().getUsername());
        return "댓글 삭제 완료!";
    }
}