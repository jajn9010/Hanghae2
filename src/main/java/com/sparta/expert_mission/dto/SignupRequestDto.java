package com.sparta.expert_mission.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요!")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$", message = "아이디를 3~12자로 입력해주세요! (특수문자 x)")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요!")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요!")
    private String checkpw;

    @NotBlank(message = "이메일을 입력해주세요!")
    @Email
    private String email;

    private boolean admin = false;
    private String adminToken = "";
}
