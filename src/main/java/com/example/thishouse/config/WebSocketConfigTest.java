package com.example.thishouse.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigTest implements WebSocketMessageBrokerConfigurer {




    //SocketJs Fallback 이용해 노출할 STOMP endPoint
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {

        stompEndpointRegistry.addEndpoint("/stomp/chat").setAllowedOriginPatterns("*").addInterceptors(new CustomHttpSessionHandshakeInterceptor()).withSockJS();
        stompEndpointRegistry.addEndpoint("/stomp/a1").setAllowedOriginPatterns("*").withSockJS();
    }



    //메시지 브로커에 관련된 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {


        registry.setApplicationDestinationPrefixes("/pub");

        registry.enableSimpleBroker("/sub");

    }

}
