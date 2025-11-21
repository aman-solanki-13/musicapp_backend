package com.appoloComputers.Spotify_Backend.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;
    
    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int duration;
}
