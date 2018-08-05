package com.lionsinvests.cleanertycoon.game.statemachine;

public class EventListener {

    private final StateMachine stateMachine;

    public EventListener(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void onEvent(StateId stateId) {
        stateMachine.changeState(stateId);
    }
}
