package com.example.thishouse.controller;

import com.example.thishouse.domain.Alarm;
import com.example.thishouse.domain.ChatSession;
import com.example.thishouse.domain.Message;
import com.example.thishouse.service.AlarmService;
import com.example.thishouse.service.ChatService;
import com.example.thishouse.service.ChatSessionStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {
  
    private final SimpMessagingTemplate template;
    private final ChatService chatService;
    private final ChatSessionStorage chatSessionStorage;
    private final AlarmService alarmService;

    @MessageMapping("/chat/list")
    public void enter(Message message, SimpMessageHeaderAccessor accessor){
        ChatSession chatSession = (ChatSession) accessor.getSessionAttributes().get("chatSession");


        chatSessionStorage.userJoined(chatSession);
        alarmService.alchecked(chatSession.getUserId(), chatSession.getRoomId());
        

        chatService.messageStatus(message.getRoomId(), message.getSender());
        List<Message> allMessage = chatService.findAllMessage(message.getRoomId());


        template.convertAndSend("/sub/chat/room/"+message.getRoomId(),allMessage);

    }





    //메시지 주고받기
    @MessageMapping("/chat/message")
    public void message(Message message,SimpMessageHeaderAccessor accessor){
        ChatSession chatSession = (ChatSession) accessor.getSessionAttributes().get("chatSession");
        String roomId = chatSession.getRoomId();
        String userId = chatSession.getUserId();
        System.out.println("userId = " + userId);
        System.out.println("roomId = " + roomId);

        boolean chatRoomOccupied = chatSessionStorage.isChatRoomOccupied(roomId);


        if(chatRoomOccupied){
            message.setMsgchk(1);
        }else {
            message.setMsgchk(0);
        }

        if(message.getMsgchk() == 0){

            String buyerOrSellerId = alarmService.getBuyerOrSellerId(message.getSender(), message.getRoomId());

            Alarm alarm = new Alarm(buyerOrSellerId,message.getSender(),message.getRoomId(),message.getMsg(),message.getMsgdate());

            alarmService.insertAlarm(alarm);
            int distinctHouseNum = alarmService.getDistinctHouseNum(message.getRoomId());
            alarm.setHousenum(distinctHouseNum);
            System.out.println("distinctHouseNum = " + distinctHouseNum);
            List<Alarm> alarms = alarmService.alarmList(alarm.getUser_id());
            template.convertAndSend("/sub/chat/a1list/"+alarm.getUser_id(),alarms);
       }

        System.out.println("현재 메시지 읽음 여부 = "  +message.getMsgchk());
        chatService.createMessage(message);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId()+"/new", message);

    }

    @MessageMapping("/chat/action")
    public void leave(String action,SimpMessageHeaderAccessor accessor){
        WebSocketSession webSocketSession = (WebSocketSession) accessor.getSessionAttributes().get("WS_SESSION");

        if ("leave".equals(action)) {
            ChatSession chatSession = (ChatSession) accessor.getSessionAttributes().get("chatSession");
            System.out.println("chatSession = " + chatSession.getUserId());
            System.out.println("chatSession = " + chatSession.getRoomId());
            if (chatSession != null) {
                chatSessionStorage.userLeft(chatSession.getUserId(), chatSession.getRoomId());

                if (webSocketSession != null) {
                    try {
                        webSocketSession.close();
                    } catch (IOException e) {
                        // 에러 처리
                        log.info(e.getMessage());
                    }
                }

                accessor.getSessionAttributes().remove("chatSession");
            }
        }

    }





}
