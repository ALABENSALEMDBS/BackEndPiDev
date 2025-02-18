package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Clubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubsRepo extends JpaRepository<Clubs, Integer> {
}
