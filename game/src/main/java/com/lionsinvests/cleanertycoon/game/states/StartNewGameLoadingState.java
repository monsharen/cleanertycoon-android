package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.os.AsyncTask;

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

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                gameLogic.startNewGame();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                eventListener.onEvent(StateId.MAIN_SCREEN);
            }
        }.execute();
    }

    @Override
    public void end() {

    }
}
