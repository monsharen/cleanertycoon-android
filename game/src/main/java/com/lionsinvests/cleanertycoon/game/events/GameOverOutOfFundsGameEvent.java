package com.lionsinvests.cleanertycoon.game.events;

import android.content.Context;
import android.util.Log;
import com.lionsinvests.cleanertycoon.game.GameEvent;

public class GameOverOutOfFundsGameEvent implements GameEvent {

    private final Context context;

    public GameOverOutOfFundsGameEvent(Context context) {
        this.context = context;
    }

    @Override
    public void execute() {
        Log.d(GameOverOutOfFundsGameEvent.class.getSimpleName(), "Game over!");
    }
}
