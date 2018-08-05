package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.widget.Button;
import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.R;
import com.lionsinvests.cleanertycoon.game.statemachine.EventListener;
import com.lionsinvests.cleanertycoon.game.statemachine.Session;
import com.lionsinvests.cleanertycoon.game.statemachine.State;

import java.util.Timer;

public class PauseState implements State {

    private Timer timer = null;

    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        activity.setContentView(R.layout.activity_main);
        final Button button = activity.findViewById(R.id.playPauseButton);
        button.setText("Play");
        gameLogic.getTimePlayed().pause();
    }

    @Override
    public void end() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
