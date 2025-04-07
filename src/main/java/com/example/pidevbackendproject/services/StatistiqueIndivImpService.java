package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.StatistiqueIndiv;
import com.example.pidevbackendproject.repositories.StatistiqueIndivRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatistiqueIndivImpService implements IStatistiqueIndivService{
    StatistiqueIndivRepo statistiqueIndivRepo;
    public StatistiqueIndiv addStatistiqueIndiv(StatistiqueIndiv statistiqueIndiv) {
        return statistiqueIndivRepo.save(statistiqueIndiv);
    }

    public void deleteStatistiqueIndiv(int idStatistiqueIndiv) {
      statistiqueIndivRepo.deleteById(idStatistiqueIndiv);
    }

    public StatistiqueIndiv modifyStatistiqueIndiv(StatistiqueIndiv statistiqueIndiv) {
        return statistiqueIndivRepo.save(statistiqueIndiv);
    }

    public List<StatistiqueIndiv> getAllStatistiqueIndiv() {
        return statistiqueIndivRepo.findAll();
    }

    public StatistiqueIndiv getStatistiqueIndivById(int idStatistiqueIndiv) {
        return statistiqueIndivRepo.findById(idStatistiqueIndiv).get();
    }





    public List <StatistiqueIndiv> getStatistiqueIndivByJoueurNumero(int numeroJoueur) {
        List<StatistiqueIndiv> statistiqueIndiv = statistiqueIndivRepo.findByJoueurstatistiqueIndiv_NumeroJoueur(numeroJoueur);
        if (!statistiqueIndiv.isEmpty()) {
            return statistiqueIndiv;
        } else {
            return null;
        }
    }
}
