package com.example.thishouse.config;

import com.example.thishouse.domain.ChatSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class CustomHttpSessionHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();

            String uriString = request.getURI().toString();
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(uriString).build();
            String roomId = uriComponents.getQueryParams().getFirst("roomId");



            String userId = (String) session.getAttribute("user_id");
            ChatSession chatSession = new ChatSession();
            chatSession.setUserId(userId);
            chatSession.setRoomId(roomId);


            attributes.put("chatSession",chatSession ); // Add session information to attributes
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
