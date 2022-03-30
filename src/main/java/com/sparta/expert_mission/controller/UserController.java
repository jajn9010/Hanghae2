package com.sparta.expert_mission.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.expert_mission.dto.SignupRequestDto;
import com.sparta.expert_mission.model.User;
import com.sparta.expert_mission.repository.UserRepository;
import com.sparta.expert_mission.service.KakaoUserService;
import com.sparta.expert_mission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService, UserRepository userRepository) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
        this.userRepository = userRepository;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model) {
        model.addAttribute("requestDto", new SignupRequestDto());
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(Model model, @Valid @ModelAttribute("requestDto") SignupRequestDto requestDto, BindingResult bindingResult) {

        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if(found.isPresent()){
            FieldError fieldError = new FieldError("requestDto", "username", "이미 존재하는 ID입니다.");
            bindingResult.addError(fieldError);
        }

        if(!requestDto.getPassword().equals(requestDto.getCheckpw())){
            FieldError fieldError = new FieldError("requestDto", "checkpw", "암호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
        }

        if(requestDto.getPassword().contains(requestDto.getUsername())) {
            FieldError fieldError = new FieldError("requestDto", "password", "비밀번호에 닉네임과 같은 내용을 넣을 수 없습니다.");
            bindingResult.addError(fieldError);
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user","null");
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