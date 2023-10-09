package com.example.thishouse.domain.house;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class House_option {
    private int house_option_num;
    private int house_num;
    private String induction;
    private String microwave;
    private String air_con;
    private String washing_machine;
    private String tv;
    private String closet;
    private String bed;
    private String a_table;
    private String shoe_closet;
    private String bidet;
    private String gas_range;
    private String refrig;
    private String electronic_door;
}
