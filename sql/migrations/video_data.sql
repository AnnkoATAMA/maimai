CREATE DATABASE playlist_db;

USE playlist_db;

CREATE TABLE videos (

                        id INT PRIMARY KEY AUTO_INCREMENT,

                        title VARCHAR(255) NOT NULL,

                        url VARCHAR(255) NOT NULL,

                        description TEXT

); 