package com.sparta.expert_mission.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message = "아이디는 영어 대소문자, 숫자 포함 4자~12자로 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "비밀번호는 영어 대소문자, 숫자 포함 8자~16자로 입력해주세요")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    private String email;
}
