package com.lionsinvests.cleanertycoon.game.statemachine;

import android.app.Activity;
import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.events.GameOverOutOfFundsState;
import com.lionsinvests.cleanertycoon.game.states.HireState;
import com.lionsinvests.cleanertycoon.game.states.PauseState;
import com.lionsinvests.cleanertycoon.game.states.PlayState;
import com.lionsinvests.cleanertycoon.game.states.RecruitmentState;

import java.util.HashMap;
import java.util.Map;

public class StateRegistry {

    private Map<StateId, State> STATES = new HashMap<>();

    public void register() {
        STATES.put(StateId.INIT, new InitalState());
        STATES.put(StateId.PAUSED, new PauseState());
        STATES.put(StateId.PLAYING, new PlayState());
        STATES.put(StateId.GAME_OVER_OUT_OF_FUNDS, new GameOverOutOfFundsState());
        STATES.put(StateId.RECRUITMENT, new RecruitmentState());
        STATES.put(StateId.HIRE, new HireState());
    }

    public State get(StateId stateId) {
        return STATES.get(stateId);
    }

    private class InitalState implements State {

        @Override
        public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {

        }

        @Override
        public void end() {

        }
    }
}
