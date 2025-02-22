package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Joueurs extends Users {
    String posteJoueur;
    int numeroJoueur;
    Date debutContratJoueur;
    Date finContratJoueur;

//    @JsonIgnore
    @ManyToOne
    Formations formation;
    @JsonIgnore
    @OneToOne
    StatistiqueIndiv statistiqueIndiv;
    @JsonIgnore
    @OneToOne
    FicheMedicales ficheMedicale;
   // @JsonIgnore
    @ManyToOne
    SousGroupes sousGroupe;
}
