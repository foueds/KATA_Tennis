package com.tennis;

public class Player {

    private int id;
    private String name;
    private int score;
    private boolean isWinner;
    private boolean hasAdvantage;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
        this.isWinner = false;
        this.hasAdvantage = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public boolean isHasAdvantage() {
        return hasAdvantage;
    }

    public void setHasAdvantage(boolean hasAdvantage) {
        this.hasAdvantage = hasAdvantage;
    }
}
