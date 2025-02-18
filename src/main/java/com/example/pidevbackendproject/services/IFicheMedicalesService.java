package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.FicheMedicales;

import java.util.List;

public interface IFicheMedicalesService {
    FicheMedicales addFicheMedicales(FicheMedicales ficheMedicale);
    void deleteFicheMedicales(int idFicheMedicale);
    FicheMedicales modifyFicheMedicales(FicheMedicales ficheMedicale);
    List<FicheMedicales> getAllFicheMedicales();
    FicheMedicales getFicheMedicalesById(int idFicheMedicale);
}
