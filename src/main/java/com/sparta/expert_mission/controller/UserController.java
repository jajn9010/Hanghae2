package com.sparta.expert_mission.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.expert_mission.dto.SignupRequestDto;
import com.sparta.expert_mission.service.KakaoUserService;
import com.sparta.expert_mission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@ModelAttribute @Valid SignupRequestDto requestDto, Errors errors, Model model) {
        System.out.println(requestDto.getPassword());
        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메세지를 핸들링함
            Map<String, String> validResult = userService.validateHandling(errors);
            for (String key : validResult.keySet()) {
                model.addAttribute(key, validResult.get(key));
                System.out.println(key);
                System.out.println(validResult.get(key));
            }
            return "signup";
        }
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}