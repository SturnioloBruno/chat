package com.chat.socket.ms_chat_socket.repository;

import com.chat.socket.ms_chat_socket.entity.Message;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ChatSocketRepository implements IChatSocketRepository{

    private static final String COLLETION_NAME = "chats";

    @Override
    public String save(Message message, String roomId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference rooms = db.collection(COLLETION_NAME);
        DocumentReference roomABC = rooms.document(roomId);
        CollectionReference messages = roomABC.collection("messages");
        ApiFuture<DocumentReference> result = messages.add(message);

        return result.get().getId();
    }

    @Override
    public List<Message> findByRoomId(String roomId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference rooms = db.collection(COLLETION_NAME);
        DocumentReference roomToFind = rooms.document(roomId);
        Iterable<DocumentReference> documentReference = roomToFind.collection("messages").listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Message> messageList = new ArrayList<>();

        while (iterator.hasNext()) {
            DocumentReference currentMessage = iterator.next();
            ApiFuture<DocumentSnapshot> future = currentMessage.get();
            DocumentSnapshot document = future.get();

            Message message = document.toObject(Message.class);
            messageList.add(message);
        }

        return messageList;
    }
}
