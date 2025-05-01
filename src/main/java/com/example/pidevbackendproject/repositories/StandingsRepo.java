package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
import com.example.pidevbackendproject.entities.Standings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StandingsRepo extends JpaRepository<Standings, Integer> {

    Standings findByClub(Clubs club);

    Optional<Standings> findByClubAndCompetition(Clubs club, Competition competition);
}
