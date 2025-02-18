package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.SousGroupes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousGroupesRepo extends JpaRepository<SousGroupes, Integer> {
}
