package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.StatistiqueMatchs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistiqueMatchsRepo extends JpaRepository<StatistiqueMatchs, Integer> {
}
