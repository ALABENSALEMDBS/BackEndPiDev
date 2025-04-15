package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciceRetablissements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idExerciceRetablissement;
    String nomExerciceRetablissement;
    String descriptionExerciceRetablissement;
    int dureeExercice;
    String niveauDifficulte ;
    @JsonIgnore
    @ManyToOne
    Rapports rapportExerciceRetablissement;

    @ManyToOne
    Nourriture nourriture;
    @OneToMany(mappedBy = "exerciceRetablissements")
    @JsonIgnore
    Set<FicheMedicales>ficheMedicales;

}
