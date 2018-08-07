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
    public void init(final Activity activity, final Session session, final GameLogic gameLogic, final EventListener eventListener) {
        activity.setContentView(R.layout.activity_loading);

        new Runnable() {

            @Override
            public void run() {
                gameLogic.startNewGame();

            }
        }.run();
        eventListener.onEvent(StateId.MAIN_SCREEN);
    }

    @Override
    public void end() {

    }
}
