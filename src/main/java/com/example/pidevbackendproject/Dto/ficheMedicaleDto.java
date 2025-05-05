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
    int idExerciceRetablissement;
    String  userPhotoUrl;
    String telephoneUser;
    String emailUser;


    public ficheMedicaleDto(int idFicheMedicale,
                            Float poidsFicheMedicale,
                            Float tailleFicheMedicale,
                            LocalDate dateBlessure,
                            String gravite,
                            String type,
                            Integer joueurId,
                            int idExerciceRetablissement) {
        this.idFicheMedicale = idFicheMedicale;
        this.poidsFicheMedicale = poidsFicheMedicale;
        this.tailleFicheMedicale = tailleFicheMedicale;
        this.dateBlessure = dateBlessure;
        this.gravite = gravite;
        this.type = type;
        this.joueurId = joueurId;
        this.idExerciceRetablissement = idExerciceRetablissement;
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
                            String joueurFullName,
                            String  userPhotoUrl,
                            String emailUser,
                            String telephoneUser
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
        this.userPhotoUrl = userPhotoUrl;
        this.emailUser=emailUser;
        this.telephoneUser=telephoneUser;
    }

    public  ficheMedicaleDto(Long id ,LocalDateTime dateConsultation,String joueurFullName,Integer joueurId){
        this.joueurFullName = joueurFullName;
        this.dateConsultation=dateConsultation;
        this.id=id;
        this.joueurId = joueurId;

    }
}