package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.Dto.consultationDto;
import com.example.pidevbackendproject.entities.Consultation;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.repositories.ConsultationRepo;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ConsultationImpService implements IConsultationServise {
    ConsultationRepo consultationRepo;
    JoueursRepo joueursRepo;
    public Consultation addConsultation(Consultation c) {
       // Joueurs j =joueursRepo.findById(id).get();
       // c.setJoueur(j);

        return consultationRepo.save(c);
    }

    public List<consultationDto> getAllConsultations() {

        return consultationRepo.findAllWithJoueurFullNameConsultation();
    }

    public Consultation modifyconsultation(Consultation c, Long idc) {
        Consultation existing = consultationRepo.findById(idc)
                .orElseThrow(() -> new RuntimeException("Consultation introuvable avec l'id : " + idc));

        // Mise à jour des champs (ajuste selon ton entité)
        existing.setDateConsultation(c.getDateConsultation());
        existing.setDescription(c.getDescription());
        existing.setJoueur(c.getJoueur());

        return consultationRepo.save(existing); // ✅ Un seul paramètre
    }



}
