package com.example.thishouse.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends PageVO{

    @NotNull
    private int user_num;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String user_id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String user_pw;


    @NotBlank(message = "회원 이름을 입력해주세요.")
    private String user_name;
    private String user_phone;
    private String user_create_time;

    private String search_name;
    private String search_content;
}
