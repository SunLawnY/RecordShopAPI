package com.Northcoders.RecordShop.Service;

import com.Northcoders.RecordShop.Entity.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbum();
    Album getAlbumById(Long id);
    Album addNewAlbum(Album album);
}
