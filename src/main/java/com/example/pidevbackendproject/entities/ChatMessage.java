package com.example.pidevbackendproject.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Enumerated(EnumType.STRING)
     MessageType type;
     String content;
     String sender;
     private LocalDateTime timestamp;

     @ManyToOne
     @JoinColumn(name = "sous_groupe_id")
     private SousGroupes sousGroupe;
}
