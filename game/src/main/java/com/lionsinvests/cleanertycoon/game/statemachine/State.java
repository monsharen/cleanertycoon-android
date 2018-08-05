package com.lionsinvests.cleanertycoon.game.statemachine;

import android.app.Activity;
import com.lionsinvests.cleanertycoon.game.GameLogic;

public interface State {

    void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener);
    void execute();
    void end();
}
