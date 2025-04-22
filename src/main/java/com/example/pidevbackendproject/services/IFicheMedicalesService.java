package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.Dto.ficheMedicaleDto;
import com.example.pidevbackendproject.entities.FicheMedicales;

import java.time.LocalDateTime;
import java.util.List;

public interface IFicheMedicalesService {
   /// FicheMedicales addFicheMedicales(FicheMedicales ficheMedicale);

/*
    FicheMedicales addFicheMedicalByIdJoueurs(int idPlayer,FicheMedicales ficheMedicales );
*/

    public FicheMedicales addFicheMedicales(FicheMedicales ficheMedicale, int joueurId, int idexrcice) ;

        void deleteFicheMedicales(int idFicheMedicale);
    FicheMedicales modifyFicheMedicales(FicheMedicales ficheMedicale);
    List<FicheMedicales> getAllFicheMedicales();
    FicheMedicales getFicheMedicalesById(int idFicheMedicale);
    public FicheMedicales createFicheMedicale(int idexrcice ,String name, String prenom, FicheMedicales ficheMedicales) ;


    //new methode
    List<ficheMedicaleDto> findAllWithJoueurFullName();

    List<Object[]> countInjuredPlayersByGravite();


}
