package com.example.thishouse.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends PageVO{
    private int notice_num;
    private String admin_id;
    private String notice_title;
    private String notice_content;
    private String notice_date;
    private int notice_hit;
    private String search_name;
    private String search_content;
}
