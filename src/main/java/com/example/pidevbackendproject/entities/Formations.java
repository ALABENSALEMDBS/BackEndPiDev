package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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


      @ManyToMany
      Set<Joueurs> joueurs = new HashSet<>();

@JsonIgnore

    @OneToMany(mappedBy = "formation",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}) //garder les tactics même après suppression d'une formation
    @JsonIgnoreProperties("formation")
    Set<Tactics> tactics = new HashSet<>();

}
