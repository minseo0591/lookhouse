package com.example.thishouse.service;

import com.example.thishouse.domain.house.House_item;
import com.example.thishouse.mapper.RoomMapper;
import com.example.thishouse.domain.MsgRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomMapper roomMapper;



    public String insertRoom(int house_num,String buyer){
        MsgRoom roomByHouseAndUser = roomMapper.getRoomByHouseAndUser(house_num, buyer);
        if(roomByHouseAndUser != null ){
             return roomByHouseAndUser.getRoomId();
        }
        House_item house1 = roomMapper.getHouse(house_num);
        MsgRoom msgRoom = new MsgRoom();
        MsgRoom msgRoomCreate = MsgRoom.create(house1.getHouse_num(), buyer, house1.getUser_id(), msgRoom.getRoomdate());
        roomMapper.insertRoom(msgRoomCreate);
        return msgRoomCreate.getRoomId();
    }


    public List<MsgRoom> findAllList(){

        return  roomMapper.findAllList();
    }


    //채팅방이 이미 생성이 되어있는지 확인하는 로직
    public MsgRoom getRoom(int houseId,String roomId){
        return roomMapper.getRoom(houseId,roomId);
    }


   public House_item getHouse(int house_num){
        return roomMapper.getHouse(house_num);
   }

   //내 채팅방 리스트
    public List<MsgRoom> getRoomUserList(String user_id){
        return roomMapper.getRoomUserList(user_id);
    }


    public MsgRoom getAlarmRoom(String roomid){
        return roomMapper.getAlarmRoom(roomid);
    }
}
