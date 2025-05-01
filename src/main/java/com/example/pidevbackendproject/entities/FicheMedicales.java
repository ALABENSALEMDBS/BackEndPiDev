package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FicheMedicales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idFicheMedicale;
    Float poidsFicheMedicale;
    Float tailleFicheMedicale;
    private LocalDate dateBlessure;
    private String gravite;
    private String type;

    @OneToOne(mappedBy = "ficheMedicale", fetch = FetchType.EAGER)
    @JsonIgnore
//    @JoinColumn(name = "joueur_id")
    Joueurs joueurficheMedicale;

   // @JsonIgnore
   @ManyToOne
    @JsonIgnore
    ExerciceRetablissements exerciceRetablissements;

}
