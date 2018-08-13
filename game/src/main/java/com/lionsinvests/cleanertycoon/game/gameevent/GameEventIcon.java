package com.lionsinvests.cleanertycoon.game.gameevent;

public enum GameEventIcon {
    ALERT("emote_alert.png"),
    ANGRY("emote_face_angry.png");

    private final String drawable;

    GameEventIcon(String drawable) {
        this.drawable = drawable;
    }

    public String getDrawable() {
        return drawable;
    }
}
