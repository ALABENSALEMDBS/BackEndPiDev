package com.example.pidevbackendproject.entities;

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
public class StatistiqueIndiv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idStatistiqueIndiv;
    Float vitesseStatistiqueIndiv;
    String endurenceStatistiqueIndiv;
    @OneToOne(mappedBy = "statistiqueIndiv")
    Joueurs joueurstatistiqueIndiv;
}
