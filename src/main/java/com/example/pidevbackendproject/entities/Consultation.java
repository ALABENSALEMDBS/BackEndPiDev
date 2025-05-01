package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Consultation {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 private LocalDateTime dateConsultation;

    @ManyToOne
    private Joueurs joueur;
    private String description;
}
