CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(64) NOT NULL, -- SHA-256ハッシュ格納用
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);