package com.lionsinvests.cleanertycoon.game.statemachine;

import com.lionsinvests.cleanertycoon.game.events.GameOverOutOfFundsState;
import com.lionsinvests.cleanertycoon.game.states.*;

import java.util.HashMap;
import java.util.Map;

public class StateRegistry {

    private Map<StateId, State> STATES = new HashMap<>();

    public void register() {
        STATES.put(StateId.INIT, new StartNewGameLoadingState());
        STATES.put(StateId.MAIN_SCREEN, new MainScreenState());
        STATES.put(StateId.GAME_OVER_OUT_OF_FUNDS, new GameOverOutOfFundsState());
        STATES.put(StateId.RECRUITMENT, new RecruitmentState());
        STATES.put(StateId.HIRE, new HireState());
        STATES.put(StateId.EMPLOYEE, new EmployeeState());
        STATES.put(StateId.AVAILABLE_CONTRACTS, new AvailableContractsState());
    }

    public State get(StateId stateId) {
        return STATES.get(stateId);
    }
}
