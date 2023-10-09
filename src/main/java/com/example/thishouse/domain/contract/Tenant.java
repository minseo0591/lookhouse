package com.example.thishouse.domain.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//입주자 임대인
public class Tenant {
    private int tenant_idx;
    private int house_num; //매물 방번호
    private String user_id;
    private String tenant_name;
    private String tenant_resident_number_f;
    private String tenant_resident_number_b;
    private String tenant_phone;
    private String road_address;
    private String detail_address;
}
