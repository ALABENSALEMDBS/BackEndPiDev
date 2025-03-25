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
    Float vitesseStatistiqueIndiv;  // Vitesse du joueur
    String endurenceStatistiqueIndiv; // Niveau d'endurance

    // Statistiques offensives
    int buts;  // Nombre de buts marqués
    int passesDecisives; // Nombre de passes décisives
    int tirs;  // Nombre de tirs tentés
    int tirsCadres; // Nombre de tirs cadrés

    // Statistiques défensives
    int tacles;  // Nombre de tacles réussis
    int fautesCommises; // Nombre de fautes commises
    int cartonsJaunes; // Nombre de cartons jaunes reçus
    int cartonsRouges; // Nombre de cartons rouges reçus

    // Autres statistiques
    int passesReussies; // Nombre de passes réussies
    int dribblesReussis; // Nombre de dribbles réussis
    int duelsGagnes; // Nombre de duels remportés
    int distanceParcourue; // Distance totale parcourue en mètres


    @ManyToOne
    Joueurs joueurstatistiqueIndiv;
}
