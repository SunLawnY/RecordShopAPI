package com.Northcoders.RecordShop.Service;

import com.Northcoders.RecordShop.Repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService implements AlbumSeriveInterface{

    @Autowired
    AlbumRepository albumRepository;

}
