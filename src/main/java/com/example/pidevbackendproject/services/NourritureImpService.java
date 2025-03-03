package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Nourriture;
import com.example.pidevbackendproject.repositories.NourritureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NourritureImpService implements INourritureService{
    @Autowired
    NourritureRepo nourritureRepo;

    public Nourriture addNourriture(Nourriture nourriture) {
        return nourritureRepo.save(nourriture);
    }

    public void deleteNourriture(int idNourriture) {
        nourritureRepo.deleteById((idNourriture));

    }

    public Nourriture modifyNourriture(Nourriture nurriture) {
        return nourritureRepo.save(nurriture);
    }

    public List<Nourriture> getAllNourriture() {
        List<Nourriture>nourritures=nourritureRepo.findAll();

        return nourritures;
    }

    public Nourriture getNourritureById(int idNourriture) {
        return nourritureRepo.findById(idNourriture).get();
    }
}
