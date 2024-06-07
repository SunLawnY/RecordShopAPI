package com.Northcoders.RecordShop.Repository;

import com.Northcoders.RecordShop.Entity.Album;
import org.springframework.data.repository.CrudRepository;

public interface albumRepository extends CrudRepository<Album, Long> {
}
