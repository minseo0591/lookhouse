package com.example.thishouse.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class MsgRoom {


    private String roomId;

    private int house_num;
    private String buyerId;  // 구매자
    private String sellerId;  // 판매자
    private Date roomdate;
    public static MsgRoom create(int house_num,String buyerId,String sellerId,Date roomdate){
        MsgRoom msgRoom = new MsgRoom();
        msgRoom.roomId = UUID.randomUUID().toString();
        msgRoom.house_num = house_num;
        msgRoom.buyerId = buyerId;
        msgRoom.sellerId = sellerId;
        msgRoom.roomdate = roomdate;
        return msgRoom;
    }





}
