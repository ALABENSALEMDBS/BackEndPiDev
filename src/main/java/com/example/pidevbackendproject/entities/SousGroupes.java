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
public class SousGroupes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idSousGroup;
    String nameSousGroup;
    int nbrJoueurSousGroup;
    @JsonIgnore
    @OneToMany(mappedBy = "sousGroupe")
    Set<Joueurs> joueurs;
    @JsonIgnore
    @OneToMany(mappedBy = "sousGroupExercice")
    Set<Exercices> exercices;
}
