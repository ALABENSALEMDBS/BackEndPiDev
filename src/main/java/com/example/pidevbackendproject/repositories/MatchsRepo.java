package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Matchs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchsRepo extends JpaRepository<Matchs, Integer> {
}
