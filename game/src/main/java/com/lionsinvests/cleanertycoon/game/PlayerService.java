package com.lionsinvests.cleanertycoon.game;

public class PlayerService {

    private static PlayerService INSTANCE = null;

    private Player player = null;

    private PlayerService() {

    }

    public static PlayerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerService();
        }

        return INSTANCE;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
