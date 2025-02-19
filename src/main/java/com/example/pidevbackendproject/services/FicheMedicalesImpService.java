package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.FicheMedicales;
import com.example.pidevbackendproject.repositories.FicheMedicalesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FicheMedicalesImpService implements IFicheMedicalesService {
    FicheMedicalesRepo ficheMedicalesRepo;
    public FicheMedicales addFicheMedicales(FicheMedicales ficheMedicale) {
        return ficheMedicalesRepo.save(ficheMedicale);
    }

    public void deleteFicheMedicales(int idFicheMedicale) {
       ficheMedicalesRepo.deleteById(idFicheMedicale);
    }

    public FicheMedicales modifyFicheMedicales(FicheMedicales ficheMedicale) {
        return ficheMedicalesRepo.save(ficheMedicale);
    }

    public List<FicheMedicales> getAllFicheMedicales() {
        return ficheMedicalesRepo.findAll();
    }

    public FicheMedicales getFicheMedicalesById(int idFicheMedicale) {
        return ficheMedicalesRepo.findById(idFicheMedicale).get();
    }
}
