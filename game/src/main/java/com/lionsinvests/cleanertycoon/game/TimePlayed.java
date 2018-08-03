package com.lionsinvests.cleanertycoon.game;

public class TimePlayed {

    private boolean paused = false;

    private Player player;

    public TimePlayed(Player player) {
        this.player = player;
    }

    public void tick() {

        player.setTimePlayed(player.getTimePlayed() + 1);
    }

    public boolean isPaused() {
        return paused;
    }

    public void start() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public float getWeeks() {
        return player.getTimePlayed() % 52;
    }

    public double getYears() {
        return Math.floor(player.getTimePlayed() / 52);
    }

}
