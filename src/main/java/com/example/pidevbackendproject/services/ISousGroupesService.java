package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.SousGroupes;

import java.util.List;

public interface ISousGroupesService {
    SousGroupes addSousGroupes(SousGroupes sousGroupe);
    void deleteSousGroupes(int idSousGroup);
    SousGroupes modifySousGroupes(int idSousGroup, SousGroupes sousGroupe);
    List<SousGroupes> getAllSousGroupes();
    SousGroupes getSousGroupesById(int idSousGroup);
}
