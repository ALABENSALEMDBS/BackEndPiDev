package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.EvenementInternes;
import com.example.pidevbackendproject.repositories.EvenementInternesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementInternesImpService implements IEvenementInternesService {
    EvenementInternesRepo evenementInternesRepo;
    public EvenementInternes addEvenementInternes(EvenementInternes evenementInterne) {
        return evenementInternesRepo.save(evenementInterne);
    }

    public void deleteEvenementInternes(int idEvenementInterne) {
          evenementInternesRepo.deleteById(idEvenementInterne);
    }

    public EvenementInternes modifyEvenementInternes(EvenementInternes evenementInterne) {
        return evenementInternesRepo.save(evenementInterne);
    }

    public List<EvenementInternes> getAllEvenementInternes() {
        List<EvenementInternes> evenementInternes = evenementInternesRepo.findAll();
        return evenementInternes ;
    }

    public EvenementInternes getEvenementInternesById(int idEvenementInterne) {
        return evenementInternesRepo.findById(idEvenementInterne).get();
    }
}
