package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Rapports;
import com.example.pidevbackendproject.repositories.RapportsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RapportsImpService implements IRapportsService {

    RapportsRepo rapportsRepo;
    public Rapports addRapports(Rapports rapport) {
        return rapportsRepo.save(rapport);
    }

    public void deleteRapports(int idRapport) {
    rapportsRepo.deleteById(idRapport);
    }

    public Rapports modifyRapports(Rapports rapport) {
        return rapportsRepo.save(rapport);
    }

    public List<Rapports> getAllRapports() {
        return rapportsRepo.findAll();
    }

    public Rapports getRapportsById(int idRapport) {
        return rapportsRepo.findById(idRapport).get();
    }
}
