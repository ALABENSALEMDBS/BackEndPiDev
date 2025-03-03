package com.example.pidevbackendproject.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ficheMedicaleDto {
    int idFicheMedicale;
    Float poidsFicheMedicale;
    Float tailleFicheMedicale;
    LocalDate dateBlessure;
    String gravite;
    String type;
    Integer joueurId;
    String joueurFullName;

    public ficheMedicaleDto(int idFicheMedicale,
                            Float poidsFicheMedicale,
                            Float tailleFicheMedicale,
                            LocalDate dateBlessure,
                            String gravite,
                            String type,
                            Integer joueurId) {
        this.idFicheMedicale = idFicheMedicale;
        this.poidsFicheMedicale = poidsFicheMedicale;
        this.tailleFicheMedicale = tailleFicheMedicale;
        this.dateBlessure = dateBlessure;
        this.gravite = gravite;
        this.type = type;
        this.joueurId = joueurId;
    }

    public ficheMedicaleDto(int idFicheMedicale,
                            Float poidsFicheMedicale,
                            Float tailleFicheMedicale,
                            LocalDate dateBlessure,
                            String gravite,
                            String type,
                            Integer joueurId,
                            String joueurFullName) {
        this.idFicheMedicale = idFicheMedicale;
        this.poidsFicheMedicale = poidsFicheMedicale;
        this.tailleFicheMedicale = tailleFicheMedicale;
        this.dateBlessure = dateBlessure;
        this.gravite = gravite;
        this.type = type;
        this.joueurId = joueurId;
        this.joueurFullName = joueurFullName;
    }
}