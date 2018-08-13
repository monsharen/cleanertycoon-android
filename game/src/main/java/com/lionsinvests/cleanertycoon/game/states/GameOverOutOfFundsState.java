package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.util.Log;
import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.statemachine.EventListener;
import com.lionsinvests.cleanertycoon.game.statemachine.Session;
import com.lionsinvests.cleanertycoon.game.statemachine.State;

public class GameOverOutOfFundsState implements State {

    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        Log.d(GameOverOutOfFundsState.class.getSimpleName(), "Game over!");
    }

    @Override
    public void end() {

    }
}
