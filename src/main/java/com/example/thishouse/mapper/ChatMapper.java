package com.example.thishouse.mapper;


import com.example.thishouse.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {

    //메세지 넣기
    public void createMessage(Message message);

    //읽은 메시지 처리
    public void messageStatus(@Param("roomId") String roomId, @Param("sender") String sender);

    //메시지 리스트 출력
    public List<Message> findAllMessage(String roomId);
}
