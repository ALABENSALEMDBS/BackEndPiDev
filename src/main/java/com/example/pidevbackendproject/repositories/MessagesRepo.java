package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepo extends JpaRepository<Messages, Integer> {
}
