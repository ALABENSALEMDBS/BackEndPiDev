package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Tournois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournoisRepo extends JpaRepository<Tournois, Integer> {
}
