package com.example.thishouse.controller;


import com.example.thishouse.domain.Alarm;
import com.example.thishouse.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AlarmController {
    private final SimpMessagingTemplate template;
    private final AlarmService alarmService;


    @MessageMapping("/chat/a1list")
    public void baby(Alarm alarm){

        System.out.println(alarm.getUser_id());
        List<Alarm> alarms = alarmService.alarmList(alarm.getUser_id());
        for (Alarm alarm1 : alarms) {
            System.out.println("alarm1 = " + alarm1.getAl_content());
        }

        template.convertAndSend("/sub/chat/a1list/"+alarm.getUser_id() ,alarms);
    }



}
