package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @JsonIgnore
    @OneToMany(mappedBy = "matchClub")
    Set<Clubs> clubs;

    @JsonIgnore
    @ManyToOne
    Tournois tournoi;

    @JsonIgnore
    @OneToOne
    StatistiqueMatchs statistiqueMatches;

    @JsonIgnore
    @OneToOne
    EvenementsGlobales evenementsGlobale;

    @JsonIgnore
    @OneToOne
    Formations formation;
}
