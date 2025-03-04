package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tournois {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTournoi;
    @Column(name = "name_competition",nullable = false)
    String nameTournoi;

    @Column(name = "date_competition", nullable = false)
    LocalDate debutTournoi;
    @Column(name = "fin_competition",nullable = false)
    LocalDate finTournoi;

    @JsonIgnore
    @OneToMany(mappedBy = "tournoi")
    Set<Matchs> matchesTournoi =  new HashSet<>();
}
