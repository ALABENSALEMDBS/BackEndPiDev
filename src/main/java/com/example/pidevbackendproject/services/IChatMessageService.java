package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.ChatMessage;

import java.util.List;

public interface IChatMessageService {

    ChatMessage saveMessage(ChatMessage chatMessage);
    List<ChatMessage> getAllMessages();
}
