package com.example.thishouse.controller;

import com.example.thishouse.domain.MsgRoom;
import com.example.thishouse.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{houseNum}/chat_info")
    public String createRoom(@PathVariable  int  houseNum, HttpSession session, RedirectAttributes redirectAttributes)  {
        String userId = (String) session.getAttribute("user_id");
        String roomId = roomService.insertRoom(houseNum, userId);


        redirectAttributes.addAttribute("houseNum",houseNum);
        redirectAttributes.addAttribute("roomId",roomId);
        return "redirect:/chat/chatPage";
    }

    //채팅방 상품 번호와 방 번호로 하나만 조회
    @GetMapping("/chatPage")
    public String chatPage( @RequestParam int houseNum,@RequestParam String roomId, Model model) {


            MsgRoom room = roomService.getRoom(houseNum, roomId);

            model.addAttribute("room",room);

            return "chat/room_detail"; // 채팅 페이지로 이동


    }




    //채팅방 내 아이디 기준 전체 리스트
    @GetMapping("/room")
    public String RoomList(String userId,Model model){
        List<MsgRoom> roomUserList = roomService.getRoomUserList(userId);
        model.addAttribute("roomUserList",roomUserList);
        return "chat/room";
    }
}
