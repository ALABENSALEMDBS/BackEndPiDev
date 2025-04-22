package com.example.pidevbackendproject.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class consultationDto {

    Integer joueurId;
    String joueurFullName;
    LocalDateTime dateConsultation;
    Long id;

    public  consultationDto(Long id ,LocalDateTime dateConsultation,String joueurFullName,Integer joueurId){
        this.joueurFullName = joueurFullName;
        this.dateConsultation=dateConsultation;
        this.id=id;
        this.joueurId = joueurId;

    }
}