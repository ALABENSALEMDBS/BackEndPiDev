package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;

import java.util.List;

public interface IClubsServise {

    Clubs addClubs(Clubs club);
    void deleteClubs(int idClub);
    Clubs modifyClubs(Clubs club);
    List<Clubs> getAllClubs();
    Clubs getClubsById(int idClub);
}
