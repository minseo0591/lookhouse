package com.example.thishouse.mapper;


import com.example.thishouse.domain.house.House_item;
import com.example.thishouse.domain.MsgRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomMapper {

    public void insertRoom(MsgRoom msgRoom);

    public List<MsgRoom> findAllList();

    public MsgRoom getRoom(@Param("house_num")int houseNum, @Param("roomId") String RoomNum);

    public House_item getHouse(int houseNum );

    public MsgRoom getRoomByHouseAndUser(@Param("house_num") int houseNum,@Param("buyerId")String buyerId);

    public List<MsgRoom> getRoomUserList(String user_id);

    public MsgRoom getAlarmRoom(String roomid);
}
