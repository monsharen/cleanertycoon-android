package com.lionsinvests.cleanertycoon.game.statemachine;

import com.lionsinvests.cleanertycoon.game.events.GameOverOutOfFundsState;
import com.lionsinvests.cleanertycoon.game.states.HireState;
import com.lionsinvests.cleanertycoon.game.states.PlayState;
import com.lionsinvests.cleanertycoon.game.states.RecruitmentState;
import com.lionsinvests.cleanertycoon.game.states.StartNewGameLoadingState;

import java.util.HashMap;
import java.util.Map;

public class StateRegistry {

    private Map<StateId, State> STATES = new HashMap<>();

    public void register() {
        STATES.put(StateId.INIT, new StartNewGameLoadingState());
        STATES.put(StateId.PLAYING, new PlayState());
        STATES.put(StateId.GAME_OVER_OUT_OF_FUNDS, new GameOverOutOfFundsState());
        STATES.put(StateId.RECRUITMENT, new RecruitmentState());
        STATES.put(StateId.HIRE, new HireState());
    }

    public State get(StateId stateId) {
        return STATES.get(stateId);
    }
}
