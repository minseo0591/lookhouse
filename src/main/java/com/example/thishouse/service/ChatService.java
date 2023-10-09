package com.example.thishouse.service;


import com.example.thishouse.mapper.ChatMapper;
import com.example.thishouse.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatService {

    private final ChatMapper chatMapper;


    public void createMessage(Message message){
        chatMapper.createMessage(message);
    }


    //메시지 읽음으로 변경 
    public void messageStatus(String roomId, String sender){
        chatMapper.messageStatus(roomId,sender);
    }
    //메시지 리스트 조회
    public List<Message> findAllMessage(String roomId){

        return chatMapper.findAllMessage(roomId);
    }


}
