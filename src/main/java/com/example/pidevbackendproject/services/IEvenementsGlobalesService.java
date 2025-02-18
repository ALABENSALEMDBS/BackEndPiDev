package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.EvenementsGlobales;

import java.util.List;

public interface IEvenementsGlobalesService {
    EvenementsGlobales addEvenementsGlobales(EvenementsGlobales evenementsGlobale);
    void deleteEvenementsGlobales(int idEvenementsGlobales);
    EvenementsGlobales modifyEvenementsGlobales(EvenementsGlobales evenementsGlobale);
    List<EvenementsGlobales> getAllEvenementsGlobales();
    EvenementsGlobales getEvenementsGlobalesById(int idEvenementsGlobales);
}
