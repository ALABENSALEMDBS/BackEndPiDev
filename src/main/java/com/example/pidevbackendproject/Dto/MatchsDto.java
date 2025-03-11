package com.example.pidevbackendproject.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MatchsDto {

    private String resultatMatch;
    private String dateMatch;  // Nouveau attribut pour la date du match
    private String lieuMatch;  // Nouveau attribut pour le lieu du match
    private String statusMatch; // Nouveau attribut pour l'état du match (par exemple "en cours", "terminé")
    private String typeMatch;   // Nouveau attribut pour le type du match (par exemple "amical", "championnat")
    private String arbitre;
    private String equipe1;
    private String equipe2;
    private byte[] image;
    private MultipartFile returnedImage;


}
