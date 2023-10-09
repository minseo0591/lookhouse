package com.example.thishouse.service;

import com.example.thishouse.domain.Alarm;
import com.example.thishouse.mapper.AlarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

   private final AlarmMapper alarmMapper;

    public void insertAlarm(Alarm alarm){

        alarmMapper.insertAlarm(alarm);
    }


    public String getBuyerOrSellerId(String userid, String roomid){
      return alarmMapper.getBuyerOrSellerId(userid,roomid);
    }

    public List<Alarm> alarmList(String userid){
       return  alarmMapper.alarmList(userid);
    }

    public void alchecked(String user_id, String roomid){
        alarmMapper.alchecked(user_id,roomid);
    }

    public int getDistinctHouseNum(String roomid){
       return alarmMapper.getDistinctHouseNum(roomid);
    }


}
