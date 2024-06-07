package com.Northcoders.RecordShop.Entity;

import com.Northcoders.RecordShop.Supporting.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ALBUMS")
public class Album {
    @Id
    @GeneratedValue
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

}

