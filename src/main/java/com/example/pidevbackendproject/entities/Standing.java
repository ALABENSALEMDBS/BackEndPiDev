package com.example.pidevbackendproject.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Clubs club;

    @ManyToOne
    @JoinColumn(name = "tournois_id")
    private Tournois tournois;


    int points = 0;
    int matchesPlayed = 0;
    int wins = 0;
    int draws = 0;
    int losses = 0;
    int goalsFor = 0;
    int goalsAgainst = 0;
    int goalDifference = 0;
}
