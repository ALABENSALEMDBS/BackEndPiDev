package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exercices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idExercice;
    String nameExercice;
    String descriptionExercice;
    String videoExercice;
    //String photoExercice;
    @Enumerated(EnumType.STRING)
    ExerciseType typeExercice;
    @ManyToOne
    SousGroupes sousGroupExercice;
    @ManyToOne
    Seances seanceExercice;
}
