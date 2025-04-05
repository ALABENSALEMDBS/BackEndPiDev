package com.example.pidevbackendproject.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
     MessageType type;
     String content;
     String sender;
     private LocalDateTime timestamp;
}
