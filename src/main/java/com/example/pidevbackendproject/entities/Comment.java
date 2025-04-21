package com.example.pidevbackendproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String videoId;
    private String username;
    private String content;
    private String timestamp;

//    @ManyToOne
//    @JoinColumn(name = "video_id")
//    private Video video;
}
