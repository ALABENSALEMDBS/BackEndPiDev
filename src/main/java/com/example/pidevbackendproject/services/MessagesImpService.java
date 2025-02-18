package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Messages;
import com.example.pidevbackendproject.repositories.MessagesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessagesImpService implements IMessagesService {
    MessagesRepo messagesRepo;
    public Messages addMessages(Messages message) {
        return messagesRepo.save(message);
    }

    public void deleteMessages(int idMessage) {
     messagesRepo.deleteById(idMessage);
    }

    public Messages modifyMessages(Messages message) {
        return messagesRepo.save(message);
    }

    public List<Messages> getAllMessages() {
        return messagesRepo.findAll();
    }

    public Messages getMessagesById(int idMessage) {
        return messagesRepo.findById(idMessage).get();
    }
}
