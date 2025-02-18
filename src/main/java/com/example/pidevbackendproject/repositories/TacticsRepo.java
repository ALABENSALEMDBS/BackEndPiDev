package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Tactics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacticsRepo extends JpaRepository<Tactics, Integer> {
}
