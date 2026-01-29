package com.archit.dev.gamemanager.entity;

import jakarta.persistence.*;

// LOMBOK CAN BE USED TO AVOID THE CREATION OF GETTERS AND SETTERS

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    private String genre;
    private int totalHours;
    private int rating;

    @Column(name = "status", nullable = false)
    private String status;

    public Game() {
    }

    public Game(String title, String genre, int totalHours, int rating, String status) {
        this.title = title;
        this.genre = genre;
        this.totalHours = totalHours;
        this.rating = rating;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
