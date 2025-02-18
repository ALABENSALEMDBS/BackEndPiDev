package com.example.pidevbackendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Inheritance(strategy = InheritanceType.JOINED) // Stratégie d'héritage


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idUser;
    String nameUsers;
    String prenomUser;
    String emailUser;
    String telephoneUser;

    @ManyToOne
    Clubs club;
}
