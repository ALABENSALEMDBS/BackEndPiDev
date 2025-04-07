package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitionRepo extends JpaRepository<Competition, Integer> {


}

