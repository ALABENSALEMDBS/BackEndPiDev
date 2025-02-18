package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Formations;
import com.example.pidevbackendproject.repositories.FormationsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormationsImpService implements IFormationsService {
    FormationsRepo formationsRepo;
    public Formations addFormations(Formations formation) {
        return formationsRepo.save(formation);
    }

    public void deleteFormations(int idFormation) {
     formationsRepo.deleteById(idFormation);
    }

    public Formations modifyFormations(Formations formation) {
        return formationsRepo.save(formation);
    }

    public List<Formations> getAllFormations() {
        return formationsRepo.findAll();
    }

    public Formations getFormationsById(int idFormation) {
        return formationsRepo.findById(idFormation).get();
    }
}
