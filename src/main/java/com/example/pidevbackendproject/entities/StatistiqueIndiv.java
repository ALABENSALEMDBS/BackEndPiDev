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

    // Vitesse et endurance
    Float vitesseStatistiqueIndiv;
    String endurenceStatistiqueIndiv;

    // Statistiques offensives
    int buts;
    int passesDecisives;
    int tirs;
    int tirsCadres;

    // Statistiques défensives
    int tacles;
    int fautesCommises;
    int cartonsJaunes;
    int cartonsRouges;

    // Autres statistiques
    int passesReussies;
    int dribblesReussis;
    int duelsGagnes;
    Float distanceParcourue;


    @ManyToOne
    Joueurs joueurstatistiqueIndiv;
}
