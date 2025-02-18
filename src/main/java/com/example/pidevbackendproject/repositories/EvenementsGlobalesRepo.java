package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.EvenementsGlobales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementsGlobalesRepo extends JpaRepository<EvenementsGlobales, Integer> {
}
