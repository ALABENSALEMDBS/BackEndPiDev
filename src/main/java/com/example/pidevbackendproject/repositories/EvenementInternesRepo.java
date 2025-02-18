package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.EvenementInternes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementInternesRepo  extends JpaRepository<EvenementInternes, Integer> {
}
