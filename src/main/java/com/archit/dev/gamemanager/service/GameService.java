package com.archit.dev.gamemanager.service;
import com.archit.dev.gamemanager.entity.Game;
import com.archit.dev.gamemanager.exception.GameNotFoundException;
import com.archit.dev.gamemanager.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // Post methods
    public Game createGame(Game inputGame){
        return gameRepository.save(inputGame);
    }

    // Get methods
    public List<Game> showGame(){
        return gameRepository.findAll();
    }
    public Game getGameById(Long id){
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("No game found with id: " + id));
    }

    // Put methods, entry updation
    public Game updateGameById(Long id, Game updatedGame){
        Game game = getGameById(id);
        game.setTitle(updatedGame.getTitle());
        game.setGenre(updatedGame.getGenre());
        game.setTotalHours(updatedGame.getTotalHours());
        game.setRating(updatedGame.getRating());
        game.setStatus(updatedGame.getStatus());
        return gameRepository.save(game);
    }

    // Delete methods
    public void deleteGameById(Long id){
        if(!gameRepository.existsById(id)){
            throw new GameNotFoundException("No game found with id: " + id);
        }
        gameRepository.deleteById(id);
    }
}
