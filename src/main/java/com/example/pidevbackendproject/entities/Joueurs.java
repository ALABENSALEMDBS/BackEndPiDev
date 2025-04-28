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
public class Joueurs extends Users {
    String posteJoueur;
    int numeroJoueur;
    LocalDate debutContratJoueur;
    LocalDate finContratJoueur;
    boolean isTituliare;

//    @JsonIgnore
//    @ManyToOne
//    Formations formation;
      @JsonIgnore
          @ManyToMany(mappedBy = "joueurs")
          Set<Formations> formations =new HashSet<>();



    //    @JsonIgnore
//    @OneToOne
//    StatistiqueIndiv statistiqueIndiv;
       @JsonIgnore
       @OneToMany(mappedBy = "joueurstatistiqueIndiv")
       Set<StatistiqueIndiv> statistiqueIndivs = new HashSet<>();

    @JsonIgnore
    @OneToOne
    FicheMedicales ficheMedicale;
    @JsonIgnore
    @ManyToOne
    SousGroupes sousGroupe;

}
