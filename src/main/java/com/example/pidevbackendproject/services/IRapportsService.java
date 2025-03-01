package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Rapports;

import java.util.List;

public interface IRapportsService {
    Rapports addRapports(Rapports rapport);
    void deleteRapports(int idRapport);
    Rapports modifyRapports(int idRapport, Rapports rapport);
    List<Rapports> getAllRapports();
    Rapports getRapportsById(int idRapport);
}
