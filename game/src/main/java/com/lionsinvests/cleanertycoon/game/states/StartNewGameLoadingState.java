package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;

import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.R;
import com.lionsinvests.cleanertycoon.game.statemachine.EventListener;
import com.lionsinvests.cleanertycoon.game.statemachine.Session;
import com.lionsinvests.cleanertycoon.game.statemachine.State;
import com.lionsinvests.cleanertycoon.game.statemachine.StateId;

public class StartNewGameLoadingState implements State {
    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        activity.setContentView(R.layout.activity_loading);
        gameLogic.startNewGame();
        //eventListener.onEvent(StateId.PLAYING);
    }

    @Override
    public void end() {

    }
}
