package com.example.thishouse.config;

import com.example.thishouse.domain.ChatSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;


@Log4j2
public class StompPreHandler implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (accessor.getCommand() == StompCommand.CONNECT) {
            ChatSession chatSession = (ChatSession) accessor.getSessionAttributes().get("chatSession");
            String userId = chatSession.getUserId();
            String chatRoomId = chatSession.getRoomId();
            updateChatRoomParticipants(chatRoomId, userId, true);
        }
        if (accessor.getCommand() == StompCommand.DISCONNECT) {
            ChatSession chatSession = (ChatSession) accessor.getSessionAttributes().get("chatSession");
            if (chatSession != null) {
                String userId = chatSession.getUserId();
                String chatRoomId = chatSession.getRoomId();
                updateChatRoomParticipants(chatRoomId, userId, false);
                accessor.getSessionAttributes().remove("chatSession");
            } else {

                log.warn("chatSession is null during disconnect.");
            }
        }
        return message;
    }


    private void updateChatRoomParticipants(String chatRoomId, String userId, boolean isJoining) {
        // 채팅방 참여자 정보 업데이트 (데이터베이스, 메모리 저장소 등 활용)
        if (isJoining) {
            // TODO: Add userId to the participants list of chatRoomId
            System.out.println("User " + userId + " joined Chat Room " + chatRoomId);
        } else {
            // TODO: Remove userId from the participants list of chatRoomId
            System.out.println("User " + userId + " left Chat Room " + chatRoomId);
        }
    }
}
