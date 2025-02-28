package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idSeance;
    String titleSeance;  // Title of the session
    LocalDate jourSeance; // Date of the session
    LocalTime heureDebut; // Start time of the session
    LocalTime heureFin; // End time of the session

    String typeSeance; // Type (e.g., "Training", "Match", "Recovery", "Gym")
    String description; // Detailed description of the session
    String location; // Location where the session is held

    int durationMinutes; // Duration of the session in minutes
    int intensityLevel; // Intensity from 1 to 10
    @JsonIgnore
    @OneToMany(mappedBy = "seanceExercice")
    Set<Exercices> exercises;
    @JsonIgnore
    @OneToOne
    Rapports rapport;
    @JsonIgnore
    @OneToOne
    EvenementInternes evenementInterne;

}
