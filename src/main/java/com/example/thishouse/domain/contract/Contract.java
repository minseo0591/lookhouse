package com.example.thishouse.domain.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    private int contract_idx;
    private int lessoer_idx;
    private int tenant_idx;
    private int house_num;

    private String road_address;
    private String detail_address;

    private String house_type;
    private String contract_type;
    private String house_deal_type;

    private String contract_state;

    private String lease_term_begin;
    private String lease_term_end;
    private String contract_date;
    private String day_of_delivery;

    private String special_provisions;

    private long deposit;
    private long down_payment;
    private long middle_payment;
    private String middle_payment_deadline;
    private long balance;
    private String balance_deadline;

    private String deal_type;

    private String exclusive_area2;
    private String supply_area2;


}
