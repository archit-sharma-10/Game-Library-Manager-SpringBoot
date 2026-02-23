package com.archit.dev.gamemanager.controller;
import java.util.*;

import com.archit.dev.gamemanager.dto.GameRequestDTO;
import com.archit.dev.gamemanager.dto.GameResponseDTO;
import com.archit.dev.gamemanager.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public GameResponseDTO postGames(@Valid @RequestBody GameRequestDTO requestBody){
        return gameService.createGame(requestBody);
    }

    @GetMapping
    public List<GameResponseDTO> getGames(){
        return gameService.showGame();
    }

    @GetMapping("/{id}")
    public GameResponseDTO getGameById(@PathVariable Long id){
        return gameService.getGameById(id);
    }

    @PutMapping("/{id}")
    public GameResponseDTO updateGameById(@PathVariable Long id, @Valid @RequestBody GameRequestDTO requestBody){
        return gameService.updateGameById(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public String deleteGameById(@PathVariable Long id){
        gameService.deleteGameById(id);
        return "Game with id: " + id + " was deleted successfully";
    }
}
