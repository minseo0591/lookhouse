package com.example.thishouse.domain;


import lombok.Data;

import java.util.Date;


//채팅 메시지를 주고받기 위한 DTO
//채팅방 입장, 채팅방에 메시지 보내기 두가지 상황에 맞춰 enum을 구현
//방 번호 , 보는내는 사람, 내용
@Data
public class Message {

    private int chtid;
    private String roomId; // 채팅방 번호
    private String sender;  // 보내는 이
    private String msg;  // 메시지 내용
    private Date msgdate;
    private int msgchk;

}
