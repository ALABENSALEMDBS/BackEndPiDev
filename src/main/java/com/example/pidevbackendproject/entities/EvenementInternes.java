package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvenementInternes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idEvenementInterne;

    @Temporal(TemporalType.DATE)
    Date dateEvenementInterne;

    //@Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime startDateEvenementInterne;

    //@Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime endDateEvenementInterne;

    String descriptionEvenementInterne;

    String nomEvenementInterne;

    @JsonIgnore
    @OneToOne(mappedBy = "evenementInterne")
    Seances seanceEvenementInterne;
}
