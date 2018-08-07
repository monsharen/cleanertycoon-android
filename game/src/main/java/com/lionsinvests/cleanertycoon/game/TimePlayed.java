package com.lionsinvests.cleanertycoon.game;

public class TimePlayed {

    private static final int DAYS_IN_WEEK = 7;
    private static final int WEEKS_IN_YEAR = 52;

    private boolean paused = false;

    private int days = 0;
    private int weeks = 0;
    private int years = 0;

    private Player player;

    public TimePlayed(Player player) {
        this.player = player;
    }

    public void tick() {
        player.setTimePlayed(player.getTimePlayed() + 1);
        days = (int) player.getTimePlayed() % DAYS_IN_WEEK;
        weeks = (int) Math.floor(player.getTimePlayed() / DAYS_IN_WEEK) % WEEKS_IN_YEAR;
        years = (int) Math.floor((player.getTimePlayed() / DAYS_IN_WEEK) / WEEKS_IN_YEAR);
    }

    public void togglePause() {
        paused = !paused;
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

    public int getDays() {
        return days;
    }

    public int getWeeks() {
        return weeks;
    }

    public int getYears() {
        return years;
    }

}
