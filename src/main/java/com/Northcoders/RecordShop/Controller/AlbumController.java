package com.Northcoders.RecordShop.Controller;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Service.AlbumServiceImpl;
import com.Northcoders.RecordShop.Supporting.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<?> getAlbumById(@PathVariable Long id){
        try{
            Album albumById = albumService.getAlbumById(id);
            return new ResponseEntity<>(albumById, HttpStatus.OK);
        } catch (ResponseStatusException ex){
            return new ResponseEntity<>(ex.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Album>> getByGenre(@PathVariable Genre genre){
        List<Album> albumByGenre = albumService.getByGenre(genre);
        return new ResponseEntity<>(albumByGenre, HttpStatus.OK);
    }

    @GetMapping("/year/{releasedYear}")
    public ResponseEntity<List<Album>> getAlbumByReleasedYear(@PathVariable Integer releasedYear){
        List<Album> albumByReleasedYear = albumService.getByReleasedYear(releasedYear);
        return new ResponseEntity<>(albumByReleasedYear, HttpStatus.OK);
    }

    @GetMapping("/albumname/{albumName}")
    public ResponseEntity<Album> getAlbumByName(@PathVariable String albumName){
        Album albumByName = albumService.getByAlbumName(albumName);
        return new ResponseEntity<>(albumByName, HttpStatus.OK);
    }

    @PostMapping("/addnew")
    public ResponseEntity<?> addNewAlbum(@RequestBody Album album){
        try{
            Album addNewAlbum = albumService.addNewAlbum(album);
            return new ResponseEntity<>(addNewAlbum, HttpStatus.CREATED);
        } catch (ResponseStatusException ex){
            return new ResponseEntity<>(ex.getReason(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAlbumById(@PathVariable Long id, @RequestBody Album album){
        try{
            Album updateAlbumById = albumService.updateAlbumById(id, album);
            return new ResponseEntity<>(updateAlbumById, HttpStatus.OK);
        } catch (ResponseStatusException ex){
            return new ResponseEntity<>(ex.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlbumById(@PathVariable Long id, @RequestBody Album album){
        try{
            Album deleteAlbumById = albumService.deleteAlbumById(id, album);
            return new ResponseEntity<>(deleteAlbumById, HttpStatus.ACCEPTED);
        } catch (ResponseStatusException ex){
            return new ResponseEntity<>(ex.getReason(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/instock")
    public ResponseEntity<List<Album>> getAlbumInStock(){
        return new ResponseEntity<>(albumService.getAllAlbumInStock(), HttpStatus.OK);
    }
}
