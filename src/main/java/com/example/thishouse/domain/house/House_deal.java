package com.example.thishouse.domain.house;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class House_deal {
    private int house_deal_num;
    private int house_num;
    private String deal_type;
    private int deposit;
    private int m_price;
    private int y_price;
//    private int monthly_rent;
//    private int charter_price;
}
