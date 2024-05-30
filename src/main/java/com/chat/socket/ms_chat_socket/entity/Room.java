package com.chat.socket.ms_chat_socket.entity;

import lombok.Data;

import java.util.List;

@Data
public class Room {
    private String roomId; // esto deberia ser tmb el identificador del documento del room en firestore
    //private List<Usuario> usuarios; // esto por ahora no lo uso
    private List<Message> messages;
}
