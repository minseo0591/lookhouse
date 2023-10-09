package com.example.thishouse.domain.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//집주인 임차인
public class Lessoer {
    private int lessoer_idx;
    private int house_num; //매물 방번호
    private String user_id;
    private String lessoer_name;
    private String lessoer_resident_number_f;
    private String lessoer_resident_number_b;
    private String lessoer_phone;
    private String l_road_address;
    private String l_detail_address;

}
