CREATE TABLE IF NOT EXISTS Album (
    id BIGINT PRIMARY KEY,
    artist VARCHAR(255),
    release_year INT,
    genre VARCHAR(50),
    album_name VARCHAR(255),
    stock INT
);

INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (1, 'The Beatles', 1969, 'ROCK', 'Abbey Road', 10);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (2, 'Michael Jackson', 1982, 'POP', 'Thriller', 15);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (3, 'Pink Floyd', 1973, 'ROCK', 'The Dark Side of the Moon', 20);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (4, 'Led Zeppelin', 1971, 'ROCK', 'Led Zeppelin IV', 12);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (5, 'Nirvana', 1991, 'ROCK', 'Nevermind', 18);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (6, 'Madonna', 1984, 'POP', 'Like a Virgin', 25);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (7, 'Prince', 1984, 'POP', 'Purple Rain', 22);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (8, 'Fleetwood Mac', 1977, 'ROCK', 'Rumours', 30);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (9, 'Eagles', 1976, 'ROCK', 'Hotel California', 14);
INSERT INTO Album (id, artist, release_year, genre, album_name, stock) VALUES (10, 'AC/DC', 1980, 'ROCK', 'Back in Black', 17);