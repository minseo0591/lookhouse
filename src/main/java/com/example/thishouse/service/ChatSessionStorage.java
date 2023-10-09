package com.example.thishouse.service;


import com.example.thishouse.domain.ChatSession;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class ChatSessionStorage {
    private Map<String, Set<String>> userChatRooms;


    public ChatSessionStorage() {
        userChatRooms = new ConcurrentHashMap<>();
    }

    public void userJoined(ChatSession chatSession) {
        String userId = chatSession.getUserId();
        String roomId = chatSession.getRoomId();

        userChatRooms.putIfAbsent(userId, new CopyOnWriteArraySet<>());
        userChatRooms.get(userId).add(roomId);
    }



    public void userLeft(String userId, String roomId) {
        Set<String> chatRooms = userChatRooms.get(userId);
        if (chatRooms != null) {
            chatRooms.remove(roomId);
            if (chatRooms.isEmpty()) {
                userChatRooms.remove(userId);
            }
        }
    }

    public boolean isChatRoomOccupied(String chatRoomId) {
        Set<String> usersInChatRoom = getUsersInChatRoom(chatRoomId);
        return usersInChatRoom.size() > 1;
    }

    public Set<String> getUsersInChatRoom(String chatRoomId) {
        Set<String> usersInChatRoom = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : userChatRooms.entrySet()) {
            String userId = entry.getKey();
            Set<String> chatRooms = entry.getValue();
            if (chatRooms.contains(chatRoomId)) {
                usersInChatRoom.add(userId);
            }
        }
        return usersInChatRoom;
    }
}
