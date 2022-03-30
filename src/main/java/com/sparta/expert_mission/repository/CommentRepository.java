package com.sparta.expert_mission.repository;

import com.sparta.expert_mission.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBlogIdOrderByModifiedAtDesc(Long id);
}
