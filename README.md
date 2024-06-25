# Record Shop Application
## Overview
The Record Shop Application is a Spring Boot-based RESTful API for managing a collection of music albums. It provides endpoints for CRUD operations (Create, Read, Update, Delete) on albums, as well as additional functionality to search albums by genre, release year, name, and check the availability of albums in stock.

## Features
- Retrieve all albums
- Retrieve album by ID
- Retrieve albums by genre
- Retrieve albums by release year
- Retrieve album by name
- Add a new album
- Update an existing album
- Delete an album
- Retrieve all albums that are in stock
  
## Technology Stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (for development/testing)
- Lombok
- Maven

  
## Getting Started
- Prerequisites
- Java 17 or higher
- Maven 3.x or higher
  
## Installation

1. Clone the repository:
git clone https://github.com/Northcoders/RecordShop.git

2. Navigate to the project directory:
cd RecordShop

3. Build the project using Maven:
mvn clean install

4. Run the application:
mvn spring-boot:run
The application will start on http://localhost:8080.

## API Endpoints
**Get all albums**
- URL: /api/v1/albums
- Method: GET
- Response: List of all albums

**Get album by ID**
- URL: /api/v1/{id}
- Method: GET
- URL Params: id=[Long]
- Response: Album object
  
**Get albums by genre**
- URL: /api/v1/genre/{genre}
- Method: GET
- URL Params: genre=[Genre]
- Response: List of albums filtered by genre
  
**Get albums by release year**
- URL: /api/v1/year/{releasedYear}
- Method: GET
- URL Params: releasedYear=[Integer]
- Response: List of albums filtered by release year
  
**Get album by name**
- URL: /api/v1/albumname/{albumName}
- Method: GET
- URL Params: albumName=[String]
- Response: Album object
  
**Add a new album**
- URL: /api/v1/addnew
- Method: POST
- Request Body: JSON object of Album
- Response: Created album object
  
**Update an existing album**
- URL: /api/v1/update/{id}
- Method: PUT
- URL Params: id=[Long]
- Request Body: JSON object of Album
- Response: Updated album object
  
**Delete an album**
- URL: /api/v1/delete/{id}
- Method: DELETE
- URL Params: id=[Long]
- Response: Deleted album object
  
**Get all albums in stock**
- URL: /api/v1/instock
- Method: GET
- Response: List of albums in stock

## Entity Description
**Album Entity**
**id**: Unique identifier for the album (Long)
**artist**: Name of the artist (String)
**releasedYear**: Year the album was released (Integer)
**genre**: Genre of the album (Genre Enum)
**albumName**: Name of the album (String)
**stock**: Number of albums in stock (Integer)
**imageUrl**: URL of the album's image (String)
## Genre Enum

**POP**

**HIP_HOP**

**ELECTRONIC**

**K_POP**

**LATIN**

**ROCK**
