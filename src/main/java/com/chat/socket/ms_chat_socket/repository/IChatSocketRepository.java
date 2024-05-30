package com.chat.socket.ms_chat_socket.repository;

import com.chat.socket.ms_chat_socket.entity.Message;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IChatSocketRepository {
    String save(Message message, String roomId) throws ExecutionException, InterruptedException;
    List<Message> findByRoomId(String roomId) throws ExecutionException, InterruptedException;
}
