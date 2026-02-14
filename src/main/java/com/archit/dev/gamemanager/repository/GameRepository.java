package com.archit.dev.gamemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.archit.dev.gamemanager.entity.Game;

public interface GameRepository extends JpaRepository<Game,Long> {
    boolean existsByTitleIgnoreCase(String title);
}
