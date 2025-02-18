package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Seances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeancesRepo extends JpaRepository<Seances, Integer> {
}
