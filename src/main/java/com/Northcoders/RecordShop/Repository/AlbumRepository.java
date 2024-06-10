package com.Northcoders.RecordShop.Repository;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Supporting.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findByStockGreaterThan(int stock);
    List<Album> findByGenre (Genre genre);
    List<Album> findByReleasedYear(int releasedYear);
    Album findByAlbumName (String albumName);
}
