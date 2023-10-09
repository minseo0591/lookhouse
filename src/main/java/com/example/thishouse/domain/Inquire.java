package com.example.thishouse.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inquire {
    private int inquire_num;
    private String user_id;
    private String inquire_question;
    private String inquire_q_time;
    private String inquire_answer;
}
