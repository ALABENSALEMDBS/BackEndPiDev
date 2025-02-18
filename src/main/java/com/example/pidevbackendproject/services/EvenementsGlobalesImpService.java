package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.EvenementsGlobales;
import com.example.pidevbackendproject.repositories.EvenementsGlobalesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EvenementsGlobalesImpService implements IEvenementsGlobalesService {
    EvenementsGlobalesRepo evenementsGlobalesRepo;

    public EvenementsGlobales addEvenementsGlobales(EvenementsGlobales evenementsGlobale) {
        return evenementsGlobalesRepo.save(evenementsGlobale);
    }

    public void deleteEvenementsGlobales(int idEvenementsGlobales) {
      evenementsGlobalesRepo.deleteById(idEvenementsGlobales);
    }

    public EvenementsGlobales modifyEvenementsGlobales(EvenementsGlobales evenementsGlobale) {
        return evenementsGlobalesRepo.save(evenementsGlobale);
    }

    public List<EvenementsGlobales> getAllEvenementsGlobales() {
        return evenementsGlobalesRepo.findAll();
    }

    public EvenementsGlobales getEvenementsGlobalesById(int idEvenementsGlobales) {
        return evenementsGlobalesRepo.findById(idEvenementsGlobales).get();
    }
}
