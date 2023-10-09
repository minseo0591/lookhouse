package com.example.thishouse.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report extends PageVO{

    private int report_num; // 신고 번호
    private int house_num; // 집 번호
    private String user_id; // 사용자 아이디
    private String report_title; // 신고 제목
    @NotBlank(message = "내용을 입력해주세요.")
    private String report_content; // 신고 내용
    private String report_content_pic; // 신고 내용 사진
    private String report_seller_pic; // 신고 판매자 사진
    private String report_house_pic; // 신고 집 사진
    private int close_house; // 거래가 완료된 매물 구분
    private int information_distinct; // 정보가 다른 매물 구분
    private String report_date; //등록 시간
    private int count;


}
