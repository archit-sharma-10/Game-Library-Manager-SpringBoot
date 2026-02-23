package com.archit.dev.gamemanager.repository;

import com.archit.dev.gamemanager.entity.GameDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameDetailsRepository extends JpaRepository<GameDetails, Long> {
    Optional<GameDetails> findByGameId(Long gameId);
    void deleteByGameId(Long gameId);
}
