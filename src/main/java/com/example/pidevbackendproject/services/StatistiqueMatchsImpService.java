package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.StatistiqueMatchs;
import com.example.pidevbackendproject.repositories.StatistiqueMatchsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatistiqueMatchsImpService implements IStatistiqueMatchsService {
    StatistiqueMatchsRepo statistiqueMatchsRepo;
    public StatistiqueMatchs addStatistiqueMatchs(StatistiqueMatchs statistiqueMatch) {
        return statistiqueMatchsRepo.save(statistiqueMatch);
    }

    public void deleteStatistiqueMatchs(int idStatistiqueMatch) {
    statistiqueMatchsRepo.deleteById(idStatistiqueMatch);
    }

    public StatistiqueMatchs modifyStatistiqueMatchs(StatistiqueMatchs statistiqueMatch) {
        return statistiqueMatchsRepo.save(statistiqueMatch);
    }

    public List<StatistiqueMatchs> getAllStatistiqueMatchs() {
        return statistiqueMatchsRepo.findAll();
    }

    public StatistiqueMatchs getStatistiqueMatchsById(int idStatistiqueMatch) {
        return statistiqueMatchsRepo.findById(idStatistiqueMatch).get();
    }
}
