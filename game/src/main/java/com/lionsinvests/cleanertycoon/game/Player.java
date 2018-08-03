package com.lionsinvests.cleanertycoon.game;

public class Player {

    private String companyName;
    private int funds = 10000;
    private int rating = 1;
    private float timePlayed = 0;

    public Player(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public float getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(float timePlayed) {
        this.timePlayed = timePlayed;
    }
}
