package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Seances;
import com.example.pidevbackendproject.repositories.SeancesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeancesImpService implements ISeancesService {
    SeancesRepo seancesRepo;
    public Seances addSeances(Seances seance) {
        return seancesRepo.save(seance)  ;
    }

    public void deleteSeances(int idSeance) {
    seancesRepo.deleteById(idSeance);
    }

    public Seances modifySeances(Seances seance) {
        return seancesRepo.save(seance);
    }

    public List<Seances> getAllSeances() {
        return seancesRepo.findAll();
    }

    public Seances getSeancesById(int idSeance) {
        return seancesRepo.findById(idSeance).get();
    }
}
