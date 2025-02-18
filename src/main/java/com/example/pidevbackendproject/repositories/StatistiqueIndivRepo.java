package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.StatistiqueIndiv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistiqueIndivRepo extends JpaRepository<StatistiqueIndiv, Integer> {
}
