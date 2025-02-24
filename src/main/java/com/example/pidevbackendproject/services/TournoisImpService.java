package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Formations;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.entities.Tournois;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.repositories.TacticsRepo;
import com.example.pidevbackendproject.repositories.TournoisRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TournoisImpService implements ITournoisService {
    TournoisRepo tournoisRepo;
    MatchsRepo matchsRepo;
    public Tournois addTournois(Tournois tournoi) {
        return tournoisRepo.save(tournoi);
    }



    public void deleteTournois(int idTournoi) {
        Optional<Tournois> optionalTournoi = tournoisRepo.findById(idTournoi);

        if (optionalTournoi.isPresent()) {
            Tournois tournoi = optionalTournoi.get();


            for (Matchs match : tournoi.getMatchesTournoi() ) {
                match.setTournoi(null);
                matchsRepo.save(match);
            }

            tournoisRepo.deleteById(idTournoi);
        } else {
            throw new EntityNotFoundException("Tournoi avec ID " + idTournoi + " non trouvé.");
        }
    }



    public Tournois modifyTournois(int idTournoi, Tournois tournoi)
    {
        Optional<Tournois> optionalTournoi = tournoisRepo.findById(idTournoi);

        if (!optionalTournoi.isPresent()) {
            throw new RuntimeException("tournoi non trouvé");
        }

        Tournois existingTournoi = optionalTournoi.get();
        existingTournoi.setNameTournoi(tournoi.getNameTournoi());
        existingTournoi.setDebutTournoi(tournoi.getDebutTournoi());
        existingTournoi.setFinTournoi(tournoi.getFinTournoi());

        return tournoisRepo.save(existingTournoi);
    }

    public List<Tournois> getAllTournois() {
        return tournoisRepo.findAll();
    }

    public Tournois getTournoisById(int idTournoi) {
        return tournoisRepo.findById(idTournoi).get();
    }

    public void affeterMatchATournois(int idMatch, int idTournoi) {
        Matchs match = matchsRepo.findById(idMatch).get();
        Tournois tournoi = tournoisRepo.findById(idTournoi).get();
        match.setTournoi(tournoi);
        matchsRepo.save(match);
    }
}
