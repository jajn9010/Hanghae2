package com.sparta.expert_mission.service;

import com.sparta.expert_mission.dto.CommentRequestDto;
import com.sparta.expert_mission.model.Comment;
import com.sparta.expert_mission.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void updateComment(CommentRequestDto requestDto, Long id, String username) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if(comment.getUser().equals(username)) {
            comment.updateComment(requestDto);
        }
    }

    public void deleteComment(Long id, String username) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("삭제할 댓글이 존재하지 않습니다.")
        );
        if(comment.getUser().equals(username)) {
            commentRepository.deleteById(id);
        }
    }
}
