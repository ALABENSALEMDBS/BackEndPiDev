package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCompetition;

    @Column(name = "name_competition")
    String nameCompetition;

    @Enumerated(EnumType.STRING)
    private TypeCompetition TypeC;


    /*@OneToMany(mappedBy = "competition")
    //@Column(name="liste_Des_Matchs")
    @JsonIgnore
    Set<Matchs> matchesTournoi =  new HashSet<>();*/


    @OneToMany(mappedBy = "competition")
    //@JsonIgnore // Retire cette ligne si tu veux que les matchs soient renvoyés dans la réponse JSON
    Set<Matchs> matchesTournoi = new HashSet<>();
}





