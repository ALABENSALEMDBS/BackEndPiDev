package com.example.pidevbackendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rapports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idRapport;
    Long speedRapport;
    Long accelerationRapport;
    Long endurance;         // Stamina over time
    Long muscularEndurance; // Muscle fatigue resistance
    Long aerobicCapacity;   // Oxygen efficiency
    Long anaerobicCapacity; // Short burst power
    // Strength-based attributes
    Long strength;      // Maximum force
    Long power;         // Strength + speed
    Long explosiveness; // Ability to produce a powerful movement instantly.
    Long verticalJump;  // Jump height
    Long horizontalJump;
    Long agility;       // Quick direction changes
    Long balance;       // Stability
    Long coordination;  //Efficient synchronization of body movements.
    Long reactionTime;  // Time to respond
    Long reactivity;    // Adjusting movements
    String etatRapport;
    String blessureRapport;
    @OneToOne(mappedBy = "rapport")
    Seances seancerapport;
    @OneToMany(mappedBy = "rapportExerciceRetablissement")
    Set<ExerciceRetablissements> exerciceRetablissements;
}
