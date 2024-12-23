CREATE DATABASE IF NOT EXISTS maimaidb;
USE maimaidb;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

INSERT INTO users (username, password) VALUES
('admin', SHA2('adminpassword', 256)),
('user1', SHA2('user1password', 256));