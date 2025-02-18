package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Messages;

import java.util.List;

public interface IMessagesService {
    Messages addMessages(Messages message);
    void deleteMessages(int idMessage);
    Messages modifyMessages(Messages message);
    List<Messages> getAllMessages();
    Messages getMessagesById(int idMessage);
}
