package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Tournois;
import com.example.pidevbackendproject.repositories.TacticsRepo;
import com.example.pidevbackendproject.repositories.TournoisRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournoisImpService implements ITournoisService {
    TournoisRepo tournoisRepo;
    public Tournois addTournois(Tournois tournoi) {
        return tournoisRepo.save(tournoi);
    }

    public void deleteTournois(int idTournoi) {
     tournoisRepo.deleteById(idTournoi);
    }

    public Tournois modifyTournois(Tournois tournoi) {
        return tournoisRepo.save(tournoi);
    }

    public List<Tournois> getAllTournois() {
        return tournoisRepo.findAll();
    }

    public Tournois getTournoisById(int idTournoi) {
        return tournoisRepo.findById(idTournoi).get();
    }
}
