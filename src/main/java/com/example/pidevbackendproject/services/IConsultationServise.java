package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.Dto.consultationDto;
import com.example.pidevbackendproject.entities.Consultation;
import com.example.pidevbackendproject.entities.FicheMedicales;

import java.util.List;

public interface IConsultationServise {
    public Consultation addConsultation(Consultation c);
    public List<consultationDto> getAllConsultations();
    Consultation modifyconsultation(Consultation c ,Long idc);
}
