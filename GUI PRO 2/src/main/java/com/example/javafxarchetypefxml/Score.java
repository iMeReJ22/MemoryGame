package com.example.javafxarchetypefxml;

public class Score {
    private Integer place;
    private String name;
    private Integer points;
    private String time;
    private Integer guesses;
    private Integer pairs;
    private Integer fGuesses;

    public Score(Integer place, tempScore rest) {
        this.place = place;
        name = rest.getName();
        points = rest.getPoints();
        time = rest.getTime();
        guesses = rest.getGuesses();
        pairs = rest.getPairs();
        fGuesses = rest.getfGuesses();
    }

    public Integer getPlace() {
        return place;
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

    public Integer getFGuesses() {
        return fGuesses;
    }
}
