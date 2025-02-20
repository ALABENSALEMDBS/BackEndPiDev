package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.repositories.SousGroupesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SousGroupesImpService implements ISousGroupesService {
    //********
    SousGroupesRepo sousGroupesRepo;

    public SousGroupes addSousGroupes(SousGroupes sousGroupe) {
        return sousGroupesRepo.save(sousGroupe);
    }

    public void deleteSousGroupes(int idSousGroup) {
     sousGroupesRepo.deleteById(idSousGroup);
    }

    public SousGroupes modifySousGroupes(SousGroupes sousGroupe) {
        return sousGroupesRepo.save(sousGroupe);
    }

    public List<SousGroupes> getAllSousGroupes() {
        return sousGroupesRepo.findAll();
    }

    public SousGroupes getSousGroupesById(int idSousGroup) {
        return sousGroupesRepo.findById(idSousGroup).get();
    }
}
