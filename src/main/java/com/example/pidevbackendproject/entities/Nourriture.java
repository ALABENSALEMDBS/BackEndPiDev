package com.example.pidevbackendproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Nourriture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idNourriture;
    String nom;
    String categorie;
    int calories;
    String recommandation;

@OneToMany(mappedBy = "nourriture")
@JsonIgnore
Set< ExerciceRetablissements> exerciceRetablissements=new HashSet<ExerciceRetablissements>();

}

