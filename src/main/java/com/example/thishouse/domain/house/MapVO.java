package com.example.thishouse.domain.house;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapVO {
    private int house_location_num;
    private int house_deal_num;
    private int house_num;
    private String road_num;
    private String road_address;
    private String detail_address;
    private double latitude;
    private double longitude;
    private String deal_type;
    private int deposit;
    private int m_price;
    private int y_price;
    private String save_name;
    private int approve;
    private String house_type;
    private float exclusive_area2;


    @Override
    public String toString() {
        return "MapVO{" +
                "house_location_num=" + house_location_num +
                ", house_deal_num=" + house_deal_num +
                ", house_num=" + house_num +
                ", road_num='" + road_num + '\'' +
                ", road_address='" + road_address + '\'' +
                ", detail_address='" + detail_address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", deal_type='" + deal_type + '\'' +
                ", deposit=" + deposit +
                ", m_price=" + m_price +
                ", y_price=" + y_price +
                '}';
    }
}
