package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.ChatMessage;
import com.example.pidevbackendproject.entities.SousGroupes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySousGroupe(SousGroupes sousGroupe);
}
