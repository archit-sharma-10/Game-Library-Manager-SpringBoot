package com.archit.dev.gamemanager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GameRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Genre cannot be blank")
    private String genre;

    @Min(value = 0, message = "Total hours must be non-negative")
    private int totalHours;

    @Min(value = 1, message = "Rating must at least be 1")
    @Max(value = 100, message = "Rating cannot exceed 100")
    private int rating;

    @NotBlank(message = "Status cannot be blank")
    private String status;
}
