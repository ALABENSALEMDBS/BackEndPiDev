package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Standings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandingsRepo extends JpaRepository<Standings, Integer> {

    Standings findByClub(Clubs club);
}
