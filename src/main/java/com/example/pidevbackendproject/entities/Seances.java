package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String titleSeance;
    Date jourSeance;
    @OneToMany(mappedBy = "seanceExercice")
    Set<Exercices> exercises;
    @OneToOne
    Rapports rapport;
    @OneToOne
    EvenementInternes evenementInterne;

}
