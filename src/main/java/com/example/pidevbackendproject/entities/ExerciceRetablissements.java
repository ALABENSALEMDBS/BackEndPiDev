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
public class ExerciceRetablissements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idExerciceRetablissement;
    String nomExerciceRetablissement;
    String descriptionExerciceRetablissement;
    @JsonIgnore
    @ManyToOne
    Rapports rapportExerciceRetablissement;

}
