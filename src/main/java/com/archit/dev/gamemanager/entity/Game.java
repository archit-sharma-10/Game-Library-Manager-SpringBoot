package com.archit.dev.gamemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false, unique = true)
    private String title;

    @NotBlank(message = "Genre cannot be blank")
    private String genre;

    @Min(value = 0, message = "Total hours must be non-negative")
    private int totalHours;

    @Min(value = 1, message = "Rating must at least be 1")
    @Max(value = 10, message = "Rating cannot exceed 10")
    private int rating;

    @NotBlank(message = "Status cannot be blank")
    @Column(nullable = false)
    private String status;

}
