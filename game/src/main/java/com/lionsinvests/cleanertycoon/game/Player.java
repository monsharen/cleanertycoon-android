package com.lionsinvests.cleanertycoon.game;

public class Player {

    private final Company company;
    private float timePlayed = 0;

    public Player(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public float getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(float timePlayed) {
        this.timePlayed = timePlayed;
    }
}
