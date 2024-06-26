package com.Northcoders.RecordShop.Service;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Repository.AlbumRepository;
import com.Northcoders.RecordShop.Supporting.Genre;
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
    public List<Album> getAllAlbumInStock(){
        return new ArrayList<>(albumRepository.findByStockGreaterThan(0));
    }

    @Override
    public Album getAlbumById(Long id) {
        Optional<Album> albumOptional = this.albumRepository.findById(id);
        if(albumOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with ID " + id + " not found");
        }
        return albumOptional.get();
    }

    @Override
    public Album addNewAlbum(Album album) {
        if(album.getAlbumName() == null || album.getGenre() == null || album.getArtist() == null || album.getStock() == null || album.getReleasedYear() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete info");
        }
        return albumRepository.save(album);
    }

    @Override
    public List<Album> getByGenre(Genre genre) {
        return new ArrayList<>(albumRepository.findByGenre(genre));
    }

    @Override
    public List<Album> getByReleasedYear(int releasedYear) {
        return new ArrayList<>(albumRepository.findByReleasedYear(releasedYear));
    }

    @Override
    public Album getByAlbumName(String albumName) {
        return albumRepository.findByAlbumName(albumName);
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with ID " + id + " not found, nothing to delete");
        } else {
            Album deleteAlbum = albumToDeleteOptional.get();
            this.albumRepository.delete(deleteAlbum);
            return deleteAlbum;
        }
    }
}
