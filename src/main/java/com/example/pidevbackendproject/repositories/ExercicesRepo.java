package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Exercices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicesRepo extends JpaRepository<Exercices, Integer> {
}
