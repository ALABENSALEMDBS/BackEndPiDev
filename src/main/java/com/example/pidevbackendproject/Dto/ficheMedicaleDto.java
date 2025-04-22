package com.example.pidevbackendproject.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    Integer iddescription;
    String  nomExerciceRetablissement;
    LocalDateTime dateConsultation;
    Long id;


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
                            Integer iddescription,
                            String nomExerciceRetablissement,
                            Integer joueurId,
                            String joueurFullName
                            ) {
        this.idFicheMedicale = idFicheMedicale;
        this.poidsFicheMedicale = poidsFicheMedicale;
        this.tailleFicheMedicale = tailleFicheMedicale;
        this.dateBlessure = dateBlessure;
        this.gravite = gravite;
        this.type = type;
        this.joueurId = joueurId;
        this.joueurFullName = joueurFullName;
        this.iddescription=iddescription;
        this.nomExerciceRetablissement=nomExerciceRetablissement;
    }

    public  ficheMedicaleDto(Long id ,LocalDateTime dateConsultation,String joueurFullName,Integer joueurId){
        this.joueurFullName = joueurFullName;
        this.dateConsultation=dateConsultation;
        this.id=id;
        this.joueurId = joueurId;

    }
}