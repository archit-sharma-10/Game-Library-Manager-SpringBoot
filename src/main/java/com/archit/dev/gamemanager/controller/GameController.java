package com.archit.dev.gamemanager.controller;
import java.util.*;
import com.archit.dev.gamemanager.service.GameService;
import com.archit.dev.gamemanager.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public Game postGames(@RequestBody Game requestBody){
        gameService.createGame(requestBody);
        return requestBody;
    }

    @GetMapping
    public List<Game> getGames(){
        return gameService.showGame();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id){
        return gameService.getGameById(id);
    }

    @PutMapping("/{id}")
    public Game updateGameById(@PathVariable Long id, @RequestBody Game requestBody){
        return gameService.updateGameById(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public String deleteGameById(@PathVariable Long id){
        gameService.deleteGameById(id);
        return "Game with id: " + id + " was deleted successfully";
    }
}
