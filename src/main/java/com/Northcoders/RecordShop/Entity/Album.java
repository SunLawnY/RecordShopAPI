package com.Northcoders.RecordShop.Entity;

import com.Northcoders.RecordShop.Supporting.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "ALBUM")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ARTIST")
    private String artist;

    @Column(name = "RELEASE_YEAR")
    private Integer releasedYear;

    @Column(name = "GENRE")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "ALBUM_NAME")
    private String albumName;

    @Column(name = "STOCK")
    private Integer stock;

    public Album(Long id, String artist, Integer releasedYear, Genre genre, String albumName, Integer stock) {
        this.id = id;
        this.artist = artist;
        this.releasedYear = releasedYear;
        this.genre = genre;
        this.albumName = albumName;
        this.stock = stock;
    }
}

