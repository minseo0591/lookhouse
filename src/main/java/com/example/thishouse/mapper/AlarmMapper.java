package com.example.thishouse.mapper;

import com.example.thishouse.domain.Alarm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlarmMapper {

    public  void insertAlarm(Alarm alarm);

    public String getBuyerOrSellerId(@Param("userid") String userid,@Param("roomid") String roomid);
    
    public List<Alarm> alarmList(String user_id);

    public void alchecked(@Param("user_id") String user_id , @Param("roomid") String roomid);


    public int getDistinctHouseNum(String roomid);

}
