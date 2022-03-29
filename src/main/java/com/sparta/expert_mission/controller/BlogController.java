package com.sparta.expert_mission.controller;

import com.sparta.expert_mission.model.Blog;
import com.sparta.expert_mission.repository.BlogRepository;
import com.sparta.expert_mission.dto.BlogRequestDto;
import com.sparta.expert_mission.security.UserDetailsImpl;
import com.sparta.expert_mission.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @GetMapping("/api/blogs")
    @ResponseBody
    public List<Blog> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/blogs/write")
    public String write() {
        return "write";
    }

    @PostMapping("/api/blogs/write")
    @ResponseBody
    public Blog createBlog(@RequestBody BlogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setUsername(userDetails.getUsername());
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    @PutMapping("/api/blogs/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        blogService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return id;
    }

}
