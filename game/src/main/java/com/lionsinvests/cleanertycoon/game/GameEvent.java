package com.lionsinvests.cleanertycoon.game;

import com.lionsinvests.cleanertycoon.game.gameevent.GameEventIcon;

public interface GameEvent {

    String getCaption();
    String getMessage();
    GameEventIcon getIcon();
    void execute();
}
