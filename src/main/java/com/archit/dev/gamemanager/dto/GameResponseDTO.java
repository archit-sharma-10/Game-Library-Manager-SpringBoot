package com.archit.dev.gamemanager.dto;

import lombok.Data;

@Data
public class GameResponseDTO {
    private Long id;
    private String title;
    private String genre;
    private int totalHours;
    private int rating;
    private String status;
    private String description;
    private String notes;
    private String favouriteMoment;
}
