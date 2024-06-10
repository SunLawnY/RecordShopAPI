package com.Northcoders.RecordShop.Controller;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Service.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/album")
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

    @PostMapping("/addnew")
    public ResponseEntity<Album> addNewAlbum(@RequestBody Album album){
        return new ResponseEntity<>(albumService.addNewAlbum(album), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Album> updateAlbumById(@PathVariable Long id, @RequestBody Album album){
        return new ResponseEntity<>(albumService.updateAlbumById(id, album), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Album> deleteAlbumById(@PathVariable Long id, @RequestBody Album album){
        return new ResponseEntity<>(albumService.deleteAlbumById(id, album), HttpStatus.ACCEPTED);
    }
}
