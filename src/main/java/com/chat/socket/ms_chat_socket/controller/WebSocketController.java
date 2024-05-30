package com.chat.socket.ms_chat_socket.controller;

import com.chat.socket.ms_chat_socket.dto.ChatMessage;
import com.chat.socket.ms_chat_socket.entity.Message;
import com.chat.socket.ms_chat_socket.repository.IChatSocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@CrossOrigin("*")
public class WebSocketController {

    @Autowired
    private IChatSocketRepository chatSocketRepository;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) throws ExecutionException, InterruptedException {
        System.out.println(message);

        Message mensaje = new Message();
        mensaje.setFrom(message.getUser());
        mensaje.setMessage(message.getMessage());
        chatSocketRepository.save(mensaje, roomId);
        return new ChatMessage(message.getMessage(), message.getUser());
    }

    @GetMapping("/api/chat/{roomId}")
    public ResponseEntity<List<Message>> getAllMessagesByRoom(@PathVariable String roomId) throws ExecutionException, InterruptedException {
        List<Message> result = chatSocketRepository.findByRoomId(roomId);
        return ResponseEntity.ok(result);
    }
}
