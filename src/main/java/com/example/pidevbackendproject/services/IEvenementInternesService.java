package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.EvenementInternes;

import java.util.List;

public interface IEvenementInternesService {
    EvenementInternes addEvenementInternes(EvenementInternes evenementInterne);
    void deleteEvenementInternes(int idEvenementInterne);
    EvenementInternes modifyEvenementInternes(EvenementInternes evenementInterne);
    List<EvenementInternes> getAllEvenementInternes();
    EvenementInternes getEvenementInternesById(int idEvenementInterne);
}
