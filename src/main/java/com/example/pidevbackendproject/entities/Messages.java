package com.example.pidevbackendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idMessage;
    String contentMessage;
    Date dateMessage;
    @ManyToOne
    Users expediteurMessage;
    @ManyToOne
    Users recepteurMessage;

}
