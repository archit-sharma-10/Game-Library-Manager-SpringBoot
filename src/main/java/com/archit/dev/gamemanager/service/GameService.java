package com.archit.dev.gamemanager.service;
import com.archit.dev.gamemanager.entity.Game;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class GameService {
    private List<Game> storedGames = new ArrayList<>();

    // Post methods
    public void createGame(Game inputGame){
        storedGames.add(inputGame);
    }

    // Get methods
    public List<Game> showGame(){
        return storedGames;
    }
    public Game getGameById(Long id){
        return storedGames.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    // Put methods, entry updation
    public Game updateGameById(Long id, Game updatedGame){
        Game game = getGameById(id);
        game.setTitle(updatedGame.getTitle());
        game.setGenre(updatedGame.getGenre());
        game.setTotalHours(updatedGame.getTotalHours());
        game.setRating(updatedGame.getRating());
        game.setStatus(updatedGame.getStatus());
        return game;
    }

    // Delete methods
    public void deleteGameById(Long id){
        boolean exists = storedGames.removeIf(game -> game.getId().equals(id));
        if(!exists){
            throw new RuntimeException("Game not found with id: " + id);
        }
    }
}
