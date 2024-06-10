package com.Northcoders.RecordShop.Repository;

import com.Northcoders.RecordShop.Entity.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findByStockGreaterThan(int stock);
}
