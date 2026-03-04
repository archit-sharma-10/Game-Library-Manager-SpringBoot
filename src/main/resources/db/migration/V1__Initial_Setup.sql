CREATE TABLE game (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      genre VARCHAR(255),
                      rating INT NOT NULL,
                      status VARCHAR(255) NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      total_hours INT NOT NULL,
                      CONSTRAINT UK_game_title UNIQUE (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE game_details (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              description TEXT,
                              favourite_moment VARCHAR(255),
                              notes VARCHAR(255),
                              game_id BIGINT NOT NULL,
                              CONSTRAINT UK_game_details_game UNIQUE (game_id),
                              CONSTRAINT FK_game_details_game FOREIGN KEY (game_id) REFERENCES game(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;