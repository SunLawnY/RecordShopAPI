package com.Northcoders.RecordShop.Service;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Supporting.Genre;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbum();
    Album getAlbumById(Long id);
    Album addNewAlbum(Album album);
    Album updateAlbumById(Long id, Album album);
    Album deleteAlbumById(Long id, Album album);
    List<Album> getAllAlbumInStock();
    List<Album> getByGenre (Genre genre);
    List<Album> getByReleasedYear(int releasedYear);
    Album getByAlbumName (String albumName);
}
