package com.Northcoders.RecordShop.Controller;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Supporting.Genre;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Northcoders.RecordShop.Service.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@AutoConfigureMockMvc
@SpringBootTest
class AlbumControllerTest {

    @Mock
    private AlbumServiceImpl mockAlbumServiceImpl;

    @InjectMocks
    private AlbumController albumController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(albumController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GetAllAlbumTest")
    void getAllAlbum() throws Exception {
        //Arrange
        List<Album> albums = List.of(
                new Album(1L, "Artist 1", 2000, Genre.POP, "Album 1", 100),
                new Album(2L, "Artist 2", 2005, Genre.HIP_HOP, "Album 2", 50),
                new Album(3L, "Artist 3", 2010, Genre.ELECTRONIC, "Album 3", 75)
        );
        // Act
        when(mockAlbumServiceImpl.getAllAlbum()).thenReturn(albums);

        // Assert

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Artist 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releasedYear").value(2005))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stock").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 1)].albumName").value("Album 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 2)].artist").value("Artist 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 3)].stock").value(75));

        verify(mockAlbumServiceImpl, times(1)).getAllAlbum();
    }

    @Test
    void getAlbumById() throws Exception {
        //Arrange
        Album test = new Album(1L, "Artist 1", 2000, Genre.POP, "Album 1", 100);
        Album test2 = new Album(2L, "Artist 2", 2005, Genre.HIP_HOP, "Album 2", 50);
        Album test3 = new Album(3L, "Artist 3", 2010, Genre.ELECTRONIC, "Album 3", 75);
        Long id = 1L;

        // Act
        when(mockAlbumServiceImpl.getAlbumById(id)).thenReturn(test);

        // Assert

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockAlbumServiceImpl, times(1)).getAlbumById(1L);
    }

    @Test
    @DisplayName("PostNewAlbumTest")
    void addNewAlbum() throws Exception {
        // Arrange
        Album album = new Album(4L, "Artist 4", 2023, Genre.POP, "Album 4", 500);

        // Act
        when(mockAlbumServiceImpl.addNewAlbum(album)).thenReturn(album);

        // Assert
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/album/addnew")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(album)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        //verify(mockAlbumServiceImpl, times(1)).addNewAlbum(album);
    }

    @Test
    void getByGenre() throws Exception {
        //Arrange
        List<Album> albums = List.of(
                new Album(1L, "Artist 1", 2000, Genre.HIP_HOP, "Album 1", 100),
                new Album(2L, "Artist 2", 2005, Genre.HIP_HOP, "Album 2", 50),
                new Album(3L, "Artist 3", 2010, Genre.HIP_HOP, "Album 3", 75)
        );
        Genre hiphop = Genre.HIP_HOP;
        // Act
        when(mockAlbumServiceImpl.getByGenre(hiphop)).thenReturn(albums);

        // Assert
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album/genre/HIP_HOP"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stock").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 1)].albumName").value("Album 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 2)].artist").value("Artist 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 3)].stock").value(75));

        verify(mockAlbumServiceImpl, times(1)).getByGenre(Genre.HIP_HOP);
    }

    @Test
    void getAlbumByReleasedYear() throws Exception {
        //Arrange
        List<Album> albums = List.of(
                new Album(1L, "Artist 1", 2005, Genre.POP, "Album 1", 100),
                new Album(2L, "Artist 2", 2005, Genre.HIP_HOP, "Album 2", 50),
                new Album(3L, "Artist 3", 2005, Genre.ELECTRONIC, "Album 3", 75)
        );
        // Act
        when(mockAlbumServiceImpl.getByReleasedYear(2005)).thenReturn(albums);

        // Assert
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album/year/2005"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stock").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 1)].albumName").value("Album 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 2)].artist").value("Artist 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 3)].releasedYear").value(2005));

        verify(mockAlbumServiceImpl, times(1)).getByReleasedYear(2005);
    }

    @Test
    void getAlbumByName() throws Exception {
        //Arrange
        Album test = new Album(1L, "Artist 1", 2000, Genre.POP, "Album1", 100);
        Album test2 = new Album(2L, "Artist 2", 2005, Genre.HIP_HOP, "Album2", 50);
        Album test3 = new Album(3L, "Artist 3", 2010, Genre.ELECTRONIC, "Album3", 75);
        String name = "Album3";
        // Act
        when(mockAlbumServiceImpl.getByAlbumName(name)).thenReturn(test3);

        // Assert
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album/albumname/Album3"))
                .andExpect(MockMvcResultMatchers.status().isOk());


        verify(mockAlbumServiceImpl, times(1)).getByAlbumName(name);
    }

    @Test
    void updateAlbumById() {
        // Arrange
        Long id = 1L;
        Album albumToUpdate = new Album(id, "Artist", 2000, Genre.POP, "Album", 100);
        Album updatedAlbum = new Album(id, "Updated Artist", 2001, Genre.ROCK, "Updated Album", 150);

        // Act
        when(mockAlbumServiceImpl.updateAlbumById(id, albumToUpdate)).thenReturn(updatedAlbum);
        ResponseEntity<?> responseEntity = albumController.updateAlbumById(id, albumToUpdate);

        // Assert
        verify(mockAlbumServiceImpl, times(1)).updateAlbumById(id, albumToUpdate);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedAlbum, responseEntity.getBody());
    }

    @Test
    void updateAlbumById_NotFound() {
        // Arrange
        Long id = 100L;
        Album albumToUpdate = new Album(id, "Artist", 2000, Genre.POP, "Album", 100);
        String errorMessage = "Album not found";

        // Act
        when(mockAlbumServiceImpl.updateAlbumById(id, albumToUpdate)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage));
        ResponseEntity<?> responseEntity = albumController.updateAlbumById(id, albumToUpdate);

        // Assert
        verify(mockAlbumServiceImpl, times(1)).updateAlbumById(id, albumToUpdate);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    void deleteAlbumById() {
        // Arrange
        Long id = 100L;
        Album albumToDelete = new Album(id, "Artist", 2000, Genre.POP, "Album", 100);
        String errorMessage = "Album not found";

        // Act
        when(mockAlbumServiceImpl.deleteAlbumById(id, albumToDelete)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage));
        ResponseEntity<?> responseEntity = albumController.deleteAlbumById(id, albumToDelete);

        // Assert
        verify(mockAlbumServiceImpl, times(1)).deleteAlbumById(id, albumToDelete);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    void deleteAlbumById_NotFound() {
        // Arrange
        Long id = 100L;
        Album albumToDelete = new Album(id, "Artist", 2000, Genre.POP, "Album", 100);
        String errorMessage = "Album not found";

        // Act
        when(mockAlbumServiceImpl.deleteAlbumById(id, albumToDelete)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage));
        ResponseEntity<?> responseEntity = albumController.deleteAlbumById(id, albumToDelete);

        // Assert
        verify(mockAlbumServiceImpl, times(1)).deleteAlbumById(id, albumToDelete);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    void getAlbumInStock() throws Exception {
        //Arrange
        List<Album> albums = List.of(
                new Album(1L, "Artist 1", 2005, Genre.POP, "Album 1", 100),
                new Album(2L, "Artist 2", 2005, Genre.HIP_HOP, "Album 2", 0),
                new Album(3L, "Artist 3", 2005, Genre.ELECTRONIC, "Album 3", 75)
        );
        List<Album> result = List.of(
                new Album(1L, "Artist 1", 2005, Genre.POP, "Album 1", 100),
                new Album(3L, "Artist 3", 2005, Genre.ELECTRONIC, "Album 3", 75)
        );

        // Act
        when(mockAlbumServiceImpl.getAllAlbumInStock()).thenReturn(result);

        // Assert
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album/instock"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stock").value(75))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 1)].albumName").value("Album 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 3)].artist").value("Artist 3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 3)].stock").value(75));

        verify(mockAlbumServiceImpl, times(1)).getAllAlbumInStock();
    }
}