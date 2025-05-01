package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Cup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CupRepo extends JpaRepository<Cup, Integer> {
}
