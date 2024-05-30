package com.chat.socket.ms_chat_socket.entity;

import lombok.Data;

@Data
public class Message {
    private String from; // esto seria el equivalente al username
    private String message;
    //private String room_id; // el room id ahora viene dado por el room y no el mensaje
}
