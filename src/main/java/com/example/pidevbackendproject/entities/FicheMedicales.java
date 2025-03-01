package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateBlessure;
    private String gravite;
    private String type;
    @JsonIgnore
    @OneToOne(mappedBy = "ficheMedicale")
//    @JoinColumn(name = "joueur_id")
    Joueurs joueurficheMedicale;
}
