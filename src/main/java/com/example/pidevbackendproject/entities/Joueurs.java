package com.example.pidevbackendproject.entities;

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
    @ManyToOne
    Formations formation;
    @OneToOne
    StatistiqueIndiv statistiqueIndiv;
    @OneToOne
    FicheMedicales ficheMedicale;
    @ManyToOne
    SousGroupes sousGroupe;
}
