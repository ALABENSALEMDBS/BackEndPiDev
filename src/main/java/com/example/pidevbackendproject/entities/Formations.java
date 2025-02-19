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
public class Formations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idFormation;
    String nameFormation;
    String descriptionFormation;

    @JsonIgnore
    @OneToOne(mappedBy = "formation")
    Matchs matcheformation;

    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    Set<Joueurs> joueurs;

    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    Set<Tactics> tactics;

}
