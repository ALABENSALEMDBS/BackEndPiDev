package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.StatistiqueIndiv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatistiqueIndivRepo extends JpaRepository<StatistiqueIndiv, Integer> {

    List<StatistiqueIndiv> findByJoueurstatistiqueIndiv_NumeroJoueur(int numeroJoueur);

}
