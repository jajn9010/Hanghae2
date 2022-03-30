package com.sparta.expert_mission.controller;

import com.sparta.expert_mission.model.Blog;
import com.sparta.expert_mission.repository.BlogRepository;
import com.sparta.expert_mission.dto.BlogRequestDto;
import com.sparta.expert_mission.security.UserDetailsImpl;
import com.sparta.expert_mission.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/api/blogs/{id}")
    public String getOne(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("blog", blog);
        return "detail";
    };
}
