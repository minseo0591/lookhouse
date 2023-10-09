package com.example.thishouse.domain;


import lombok.Data;

import java.util.Date;

@Data
public class Alarm {

    private String user_id;
    private String sender;
    private String roomid;
    private String al_content;
    private Date al_date;
    private int al_chk;
    private int housenum;
    private int roomid_count;

    public Alarm(String user_id, String sender, String roomid, String al_content, Date al_date) {
        this.user_id = user_id;
        this.sender = sender;
        this.roomid = roomid;
        this.al_content = al_content;
        this.al_date = al_date;
        this.al_chk= 0;
    }

    public Alarm(){

    }

    public Alarm(int housenum){
        this.housenum = housenum;
    }


}
