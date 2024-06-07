package com.Northcoders.RecordShop.Controller;

import com.Northcoders.RecordShop.Entity.Album;
import com.Northcoders.RecordShop.Supporting.Genre;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Northcoders.RecordShop.Service.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;


@AutoConfigureMockMvc
@SpringBootTest
class AlbumControllerTest {

    @Mock
    private AlbumServiceImpl albumServiceImpl;

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
    @DisplayName("GetAllAlbum")
    void getAllAlbum() throws Exception {
        //Arrange
        List<Album> albums = List.of(
                new Album(1L, "Artist 1", 2000, Genre.POP, "Album 1", 100),
                new Album(2L, "Artist 2", 2005, Genre.HIP_HOP, "Album 2", 50),
                new Album(3L, "Artist 3", 2010, Genre.ELECTRONIC, "Album 3", 75)
        );
        // Act
        when(albumServiceImpl.getAllAlbum()).thenReturn(albums);

        // Assert

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/album"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Artist 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releasedYear").value(2005))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stock").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 1)].albumName").value("Album 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 2)].artist").value("Artist 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.id == 3)].stock").value(75));

        verify(albumServiceImpl, times(1)).getAllAlbum();
    }

    @Test
    void getAlbumById() {
    }

    @Test
    void addNewAlbum() {
    }
}