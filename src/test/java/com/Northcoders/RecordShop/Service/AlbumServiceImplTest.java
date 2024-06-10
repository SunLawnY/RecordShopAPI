package com.Northcoders.RecordShop.Service;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Supporting.Genre;

import com.Northcoders.RecordShop.Repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlbumServiceImplTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAlbum() {
        // Arrange
        List<Album> mockAlbums = List.of(
                new Album(1L, "Artist 1", 2000, Genre.POP, "Album 1", 100),
                new Album(2L, "Artist 2", 2005, Genre.ROCK, "Album 2", 50)
        );
        when(mockAlbumRepository.findAll()).thenReturn(mockAlbums);

        // Act
        List<Album> albums = albumService.getAllAlbum();

        // Assert
        assertEquals(2, albums.size());
        verify(mockAlbumRepository, times(1)).findAll();
    }

    @Test
    void testGetAllAlbumInStock() {
        // Arrange
        List<Album> mockAlbums = List.of(
                new Album(1L, "Artist 1", 2000, Genre.POP, "Album 1", 100),
                new Album(2L, "Artist 2", 2005, Genre.ROCK, "Album 2", 50)
        );
        when(mockAlbumRepository.findByStockGreaterThan(0)).thenReturn(mockAlbums);

        // Act
        List<Album> albums = albumService.getAllAlbumInStock();

        // Assert
        assertEquals(2, albums.size());
        verify(mockAlbumRepository, times(1)).findByStockGreaterThan(0);
    }

    @Test
    void testGetAlbumById_Success() {
        // Arrange
        Album mockAlbum = new Album(1L, "Artist 1", 2000, Genre.POP, "Album 1", 100);
        when(mockAlbumRepository.findById(1L)).thenReturn(Optional.of(mockAlbum));

        // Act
        Album album = albumService.getAlbumById(1L);

        // Assert
        assertNotNull(album);
        assertEquals(mockAlbum.getId(), album.getId());
        verify(mockAlbumRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAlbumById_NotFound() {
        // Arrange
        when(mockAlbumRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            albumService.getAlbumById(1L);
        });
        //assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Album with ID 1 not found", exception.getReason());
        verify(mockAlbumRepository, times(1)).findById(1L);
    }

    @Test
    void testAddNewAlbum_Success() {
        // Arrange
        Album newAlbum = new Album(null, "Artist 1", 2000, Genre.POP, "Album 1", 100);
        Album savedAlbum = new Album(1L, "Artist 1", 2000, Genre.POP, "Album 1", 100);
        when(mockAlbumRepository.save(newAlbum)).thenReturn(savedAlbum);

        // Act
        Album album = albumService.addNewAlbum(newAlbum);

        // Assert
        assertNotNull(album);
        assertEquals(savedAlbum.getId(), album.getId());
        verify(mockAlbumRepository, times(1)).save(newAlbum);
    }

    @Test
    void testAddNewAlbum_IncompleteInfo() {
        // Arrange
        Album newAlbum = new Album(null, "Artist 1", 2000, Genre.POP, null, 100);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            albumService.addNewAlbum(newAlbum);
        });
        //assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Incomplete info", exception.getReason());
    }

    @Test
    void testGetByGenre() {
        // Arrange
        Genre genre = Genre.POP;
        List<Album> mockAlbums = List.of(
                new Album(1L, "Artist 1", 2000, genre, "Album 1", 100)
        );
        when(mockAlbumRepository.findByGenre(genre)).thenReturn(mockAlbums);

        // Act
        List<Album> albums = albumService.getByGenre(genre);

        // Assert
        assertEquals(1, albums.size());
        verify(mockAlbumRepository, times(1)).findByGenre(genre);
    }

    @Test
    void testGetByReleasedYear() {
        // Arrange
        int releasedYear = 2000;
        List<Album> mockAlbums = List.of(
                new Album(1L, "Artist 1", releasedYear, Genre.POP, "Album 1", 100)
        );
        when(mockAlbumRepository.findByReleasedYear(releasedYear)).thenReturn(mockAlbums);

        // Act
        List<Album> albums = albumService.getByReleasedYear(releasedYear);

        // Assert
        assertEquals(1, albums.size());
        verify(mockAlbumRepository, times(1)).findByReleasedYear(releasedYear);
    }

    @Test
    void testGetByAlbumName() {
        // Arrange
        String albumName = "Album 1";
        Album mockAlbum = new Album(1L, "Artist 1", 2000, Genre.POP, albumName, 100);
        when(mockAlbumRepository.findByAlbumName(albumName)).thenReturn(mockAlbum);

        // Act
        Album album = albumService.getByAlbumName(albumName);

        // Assert
        assertNotNull(album);
        assertEquals(mockAlbum.getAlbumName(), album.getAlbumName());
        verify(mockAlbumRepository, times(1)).findByAlbumName(albumName);
    }

    @Test
    void testUpdateAlbumById_Success() {
        // Arrange
        Long albumId = 1L;
        Album existingAlbum = new Album(albumId, "Artist 1", 2000, Genre.POP, "Album 1", 100);
        Album updateInfo = new Album(null, "Updated Artist", 2001, Genre.ROCK, "Updated Album", 50);
        when(mockAlbumRepository.findById(albumId)).thenReturn(Optional.of(existingAlbum));
        when(mockAlbumRepository.save(any(Album.class))).thenReturn(existingAlbum);

        // Act
        Album updatedAlbum = albumService.updateAlbumById(albumId, updateInfo);

        // Assert
        assertEquals("Updated Artist", updatedAlbum.getArtist());
        assertEquals(2001, updatedAlbum.getReleasedYear());
        assertEquals(Genre.ROCK, updatedAlbum.getGenre());
        assertEquals("Updated Album", updatedAlbum.getAlbumName());
        assertEquals(50, updatedAlbum.getStock());
        verify(mockAlbumRepository, times(1)).findById(albumId);
        verify(mockAlbumRepository, times(1)).save(existingAlbum);
    }

    @Test
    void testUpdateAlbumById_NotFound() {
        // Arrange
        Long albumId = 1L;
        Album updateInfo = new Album(null, "Updated Artist", 2001, Genre.ROCK, "Updated Album", 50);
        when(mockAlbumRepository.findById(albumId)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            albumService.updateAlbumById(albumId, updateInfo);
        });
        //assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Invalid ID_UPDATE", exception.getReason());
        verify(mockAlbumRepository, times(1)).findById(albumId);
    }

    @Test
    void testDeleteAlbumById_Success() {
        // Arrange
        Long albumId = 1L;
        Album existingAlbum = new Album(albumId, "Artist 1", 2000, Genre.POP, "Album 1", 100);
        when(mockAlbumRepository.findById(albumId)).thenReturn(Optional.of(existingAlbum));

        // Act
        Album deletedAlbum = albumService.deleteAlbumById(albumId, null);

        // Assert
        assertNotNull(deletedAlbum);
        assertEquals(existingAlbum.getId(), deletedAlbum.getId());
        verify(mockAlbumRepository, times(1)).findById(albumId);
        verify(mockAlbumRepository, times(1)).delete(existingAlbum);
    }

    @Test
    void testDeleteAlbumById_NotFound() {
        // Arrange
        Long albumId = 1L;
        when(mockAlbumRepository.findById(albumId)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            albumService.deleteAlbumById(albumId, null);
        });
        //assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Album with ID 1 not found, nothing to delete", exception.getReason());
        verify(mockAlbumRepository, times(1)).findById(albumId);
    }

}