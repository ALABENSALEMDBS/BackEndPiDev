package com.example.pidevbackendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @OneToOne(mappedBy = "ficheMedicale")
    Joueurs joueurficheMedicale;
}
