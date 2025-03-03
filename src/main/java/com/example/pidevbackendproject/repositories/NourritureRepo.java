package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Nourriture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NourritureRepo extends JpaRepository<Nourriture, Integer> {
}
