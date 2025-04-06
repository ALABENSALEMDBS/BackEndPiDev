package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Seances;
import com.example.pidevbackendproject.entities.Standing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandingRepo extends JpaRepository<Standing, Integer> {
}
