package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Rapports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RapportsRepo extends JpaRepository<Rapports, Integer> {
    List<Rapports> findByJoueurrapport_NumeroJoueur(int numeroJoueur);
}
