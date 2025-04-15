package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.ExerciceRetablissements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciceRetablissementsRepo extends JpaRepository<ExerciceRetablissements, Integer> {
    boolean existsByNomExerciceRetablissement(String nomExerciceRetablissement);

}
