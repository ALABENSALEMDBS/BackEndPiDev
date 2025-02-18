package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Formations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationsRepo extends JpaRepository<Formations, Integer> {
}
