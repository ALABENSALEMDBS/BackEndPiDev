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
    Float vitesseRapport;
    String enduranceRapport;
    String etatRapport;
    String blessureRapport;
    @OneToOne(mappedBy = "rapport")
    Seances seancerapport;
    @OneToMany(mappedBy = "rapportExerciceRetablissement")
    Set<ExerciceRetablissements> exerciceRetablissements;
}
