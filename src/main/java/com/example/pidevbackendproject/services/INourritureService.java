package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.Nourriture;

import java.util.List;

public interface INourritureService {
    Nourriture addNourriture(Nourriture nourriture);
    void deleteNourriture(int idNourriture);
    Nourriture modifyNourriture(Nourriture nurriture);
    List<Nourriture> getAllNourriture();
    Nourriture getNourritureById(int idNourriture);

}
