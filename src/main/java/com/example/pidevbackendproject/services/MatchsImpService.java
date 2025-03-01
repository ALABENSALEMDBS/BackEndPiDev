package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchsImpService implements IMatchsService {
    MatchsRepo matchsRepo;
    public Matchs addMatchs(Matchs match) {
        return matchsRepo.save(match);
    }

    public void deleteMatchs(int idMatch) {
     matchsRepo.deleteById(idMatch);
    }



    public Matchs modifyMatchs(int idMatch, Matchs match) 
    {
        Optional<Matchs> optionalMatchs = matchsRepo.findById(idMatch);

        if (!optionalMatchs.isPresent()) {
            throw new RuntimeException("match non trouvé");
        }

        Matchs existingMatchs = optionalMatchs.get();
        existingMatchs.setResultatMatch(match.getResultatMatch());

        return matchsRepo.save(existingMatchs);    }

    public List<Matchs> getAllMatchs() {
        return matchsRepo.findAll();
    }

    public Matchs getMatchsById(int idMatch) {
        return matchsRepo.findById(idMatch).get();
    }
}
