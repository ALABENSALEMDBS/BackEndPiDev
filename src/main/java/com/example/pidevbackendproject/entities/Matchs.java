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
public class Matchs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idMatch;
    String resultatMatch;


    @OneToMany(mappedBy = "matchClub")
    Set<Clubs> clubs;

    @ManyToOne
    Tournois tournoi;

    @OneToOne
    StatistiqueMatchs statistiqueMatches;

    @OneToOne
    EvenementsGlobales evenementsGlobale;

    @OneToOne
    Formations formation;
}
