package com.example.pidevbackendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvenementsGlobales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idEvenementsGlobales;

    @Temporal(TemporalType.DATE)
    Date dateEvenementsGlobale;

    //@Temporal(TemporalType.TIME)
    LocalTime startDateEvenementsGlobale;

    //@Temporal(TemporalType.TIME)
    LocalTime endDateEvenementsGlobale;

    String descriptionEvenementsGlobale;

    String nomEvenementsGlobale;

    @OneToOne(mappedBy = "evenementsGlobale")
    Matchs matcheEvenementsGlobale;
}
