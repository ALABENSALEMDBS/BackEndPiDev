package com.example.pidevbackendproject.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class consultationDto {

    Integer idUser;
    String joueurFullName;
    LocalDateTime dateConsultation;
    Long id;
    String description;

    public  consultationDto(Long id ,LocalDateTime dateConsultation,String description,String joueurFullName,Integer joueurId){
        this.joueurFullName = joueurFullName;
        this.dateConsultation=dateConsultation;
        this.id=id;
        this.idUser = joueurId;
        this.description=description;

    }
}