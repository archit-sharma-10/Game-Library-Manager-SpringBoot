package com.archit.dev.gamemanager.service;
import com.archit.dev.gamemanager.dto.GameRequestDTO;
import com.archit.dev.gamemanager.dto.GameResponseDTO;
import com.archit.dev.gamemanager.entity.Game;
import com.archit.dev.gamemanager.entity.GameDetails;
import com.archit.dev.gamemanager.exception.GameAlreadyExistsException;
import com.archit.dev.gamemanager.exception.GameNotFoundException;
import com.archit.dev.gamemanager.repository.GameDetailsRepository;
import com.archit.dev.gamemanager.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameDetailsRepository gameDetailsRepository;

    @Autowired
    private RawgService rawgService;

    // Post methods
    public GameResponseDTO createGame(GameRequestDTO inputGame){
        if(gameRepository.existsByTitleIgnoreCase(inputGame.getTitle())){
            throw new GameAlreadyExistsException("Game already exists with title: " + inputGame.getTitle());
        }
        Game game = mapToEntity(inputGame);
        Game savedGame = gameRepository.save(game);

        GameDetails details = new GameDetails();
        details.setGame(savedGame);
        String description = rawgService.fetchDescription(savedGame.getTitle());
        details.setDescription(description);
        details.setNotes("");
        details.setFavouriteMoment("");
        gameDetailsRepository.save(details);

        return mapToResponse(savedGame);
    }

    // Get methods
    public List<GameResponseDTO> showGame(){
        List<Game> games = gameRepository.findAll();
        List<GameResponseDTO> responseList = new ArrayList<>();
        for(Game game : games){
            responseList.add(mapToResponse(game));
        }
        return responseList;
    }
    public GameResponseDTO getGameById(Long id){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("No game found with id: " + id));
        return mapToResponse(game);
    }

    // Put methods, entry updation
    public GameResponseDTO updateGameById(Long id, GameRequestDTO updatedGame){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("No game found with id: " + id));
        if(!game.getTitle().equalsIgnoreCase(updatedGame.getTitle())){
            if(gameRepository.existsByTitleIgnoreCase(updatedGame.getTitle())){
                throw new GameAlreadyExistsException("Game already exists with title: " + updatedGame.getTitle());
            }
        }
        game.setTitle(updatedGame.getTitle());
        game.setGenre(updatedGame.getGenre());
        game.setTotalHours(updatedGame.getTotalHours());
        game.setRating(updatedGame.getRating());
        game.setStatus(updatedGame.getStatus());

        Game savedGame = gameRepository.save(game);
        return mapToResponse(savedGame);
    }

    // Delete methods
    public void deleteGameById(Long id){
        if(!gameRepository.existsById(id)){
            throw new GameNotFoundException("No game found with id: " + id);
        }
        gameDetailsRepository.deleteByGameId(id);
        gameRepository.deleteById(id);
    }

    // Mapping methods for DTO and Entity
    private Game mapToEntity(GameRequestDTO dto){
        Game game = new Game();
        game.setTitle(dto.getTitle());
        game.setGenre(dto.getGenre());
        game.setTotalHours(dto.getTotalHours());
        game.setRating(dto.getRating());
        game.setStatus(dto.getStatus());
        return game;
    }

    private GameResponseDTO mapToResponse(Game game){
        GameResponseDTO response = new GameResponseDTO();
        response.setId(game.getId());
        response.setTitle(game.getTitle());
        response.setGenre(game.getGenre());
        response.setTotalHours(game.getTotalHours());
        response.setRating(game.getRating());
        response.setStatus(game.getStatus());

        Optional<GameDetails> detailsOpt = gameDetailsRepository.findByGameId(game.getId());

        if(detailsOpt.isPresent()){
            GameDetails details = detailsOpt.get();
            response.setDescription(details.getDescription());
            response.setNotes(details.getNotes());
            response.setFavouriteMoment(details.getFavouriteMoment());
        }
        return response;
    }
}
