package com.example.thishouse.domain.community;

import com.example.thishouse.domain.PageVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Community_reply extends PageVO {
    private int reply_num;
    private int community_num;
    private String user_id;
    private String reply_contents;
    private Date reply_date;
    private String search_name;
    private String search_content;
}
