package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Clubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubsRepo extends JpaRepository<Clubs, Integer> {

    Optional<Clubs> findByNameClub(String name);


    //List<Clubs> findAllByIdClub(List<Integer> idClub);

    List<Clubs> findAllByIdClubIn(List<Integer> idClub);


}
