package com.lionsinvests.cleanertycoon.game.statemachine;

public class EventListener {

    private final StateMachine stateMachine;

    public EventListener(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public synchronized void onEvent(StateId stateId) {
        stateMachine.changeState(stateId);
    }
}
