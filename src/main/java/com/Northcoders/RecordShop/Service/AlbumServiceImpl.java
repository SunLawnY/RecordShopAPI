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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
        }
        return albumOptional.get();
    }
}
