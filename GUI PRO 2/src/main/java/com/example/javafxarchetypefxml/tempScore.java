package com.example.javafxarchetypefxml;

public class tempScore {
    private String name;
    private Integer points;
    private String time;
    private Integer guesses;
    private Integer pairs;
    private Integer fGuesses;

    public tempScore(String name, Integer points, String time, Integer guesses, Integer pairs, Integer fGuesses) {
        this.name = name;
        this.points = points;
        this.time = time;
        this.guesses = guesses;
        this.pairs = pairs;
        this.fGuesses = fGuesses;
    }

    public String getName() {
        return name;
    }

    public Integer getPoints() {
        return points;
    }

    public String getTime() {
        return time;
    }

    public Integer getGuesses() {
        return guesses;
    }

    public Integer getPairs() {
        return pairs;
    }

    public Integer getfGuesses() {
        return fGuesses;
    }
}
