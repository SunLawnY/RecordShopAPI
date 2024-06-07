package com.Northcoders.RecordShop.Controller;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Service.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumServiceImpl albumService;

    @GetMapping()
    public ResponseEntity<List<Album>> getAllAlbum(){
       return new ResponseEntity<>(albumService.getAllAlbum(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id){
        Album albumById = albumService.getAlbumById(id);
        return new ResponseEntity<>(albumById, HttpStatus.OK);
    }

    @PostMapping("/addNew")
    public ResponseEntity<Album> addNewAlbum(@RequestBody Album album){
        return new ResponseEntity<>(albumService.addNewAlbum(album), HttpStatus.OK);
    }
}
