package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tournois {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTournoi;

    @Column(name = "name_competition",nullable = false)
    String nameTournoi;


    @OneToMany(mappedBy = "tournois", cascade = CascadeType.ALL)
    private List<Standing> listeMatchs = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private TypeTournois TypeT;

    @JsonIgnore
    @Column(name = "date_competition", nullable = false)
    LocalDate debutTournoi;

    @JsonIgnore
    @Column(name = "fin_competition",nullable = false)
    LocalDate finTournoi;

    //@JsonIgnore
    @OneToMany(mappedBy = "tournoi")
    @Column(name="liste_Des_Matchs")
    Set<Matchs> matchesTournoi =  new HashSet<>();


    @OneToMany(mappedBy = "tournois", cascade = CascadeType.ALL)
    private List<Standing> standings = new ArrayList<>();







}
