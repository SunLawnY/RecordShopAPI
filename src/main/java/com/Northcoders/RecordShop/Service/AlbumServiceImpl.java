package com.Northcoders.RecordShop.Service;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbum(){
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Album getAlbumById(Long id) {
        Optional<Album> albumOptional = this.albumRepository.findById(id);
        if(albumOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID_GET");
        }
        return albumOptional.get();
    }

    @Override
    public Album addNewAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbumById(Long id, Album album) {
        Optional<Album> albumOptional = this.albumRepository.findById(id);
        if (albumOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID_UPDATE");
        }
        Album albumToUpdate = albumOptional.get();
        if(album.getAlbumName() != null){
            albumToUpdate.setAlbumName(album.getAlbumName());
        }
        if(album.getStock() != null){
            albumToUpdate.setStock(album.getStock());
        }
        if(album.getArtist() != null){
            albumToUpdate.setArtist(album.getArtist());
        }
        if(album.getReleasedYear() != null){
            albumToUpdate.setReleasedYear(album.getReleasedYear());
        }
        if(album.getGenre() != null){
            albumToUpdate.setGenre(album.getGenre());
        }

        return this.albumRepository.save(albumToUpdate);
    }

    @Override
    public Album deleteAlbumById(Long id, Album album){
        Optional<Album> albumToDeleteOptional = this.albumRepository.findById(id);
        if (albumToDeleteOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            Album deleteAlbum = albumToDeleteOptional.get();
            this.albumRepository.delete(deleteAlbum);
            return deleteAlbum;
        }
    }
}
