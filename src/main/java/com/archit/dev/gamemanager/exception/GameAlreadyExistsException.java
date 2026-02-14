package com.archit.dev.gamemanager.exception;

public class GameAlreadyExistsException extends RuntimeException{
    public GameAlreadyExistsException(String message){
        super(message);
    }
}
