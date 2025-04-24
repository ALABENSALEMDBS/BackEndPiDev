package com.example.pidevbackendproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Standings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Clubs club;

    @ManyToOne
    @JoinColumn(name = "tournois_id")
    @JsonIgnore
    private Tournois tournois;



    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    //@Column(updatable = false)
    int points = 0;

    int matchesPlayed = 0;

    int wins = 0;
    int draws = 0;
    int losses = 0;
    int goalsFor = 0;
    int goalsAgainst = 0;
    int goalDifference = 0;
}
