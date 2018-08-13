package com.lionsinvests.cleanertycoon.game.statemachine;

import android.app.Activity;
import android.util.Log;

import com.lionsinvests.cleanertycoon.game.GameLogic;

public class StateMachine implements LifeCycleAware {

    private final StateRegistry stateRegistry;
    private State currentState;
    private Session session;
    private Activity activity;
    private GameLogic gameLogic;
    private EventListener eventListener;

    public StateMachine(StateRegistry stateRegistry, Activity activity, GameLogic gameLogic) {
        this.stateRegistry = stateRegistry;
        session = new Session();
        this.activity = activity;
        this.gameLogic = gameLogic;
        this.eventListener = new EventListener(this);
    }

    public void initiate() {
        changeState(StateId.INIT);
    }

    public void changeState(StateId stateId) {
        Log.d(getClass().getSimpleName(), "changing state from " + currentState + " to " + stateId);
        if (currentState != null) {
            currentState.end();
        }
        currentState = stateRegistry.get(stateId);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                currentState.init(activity, session, gameLogic, eventListener);
            }
        });

    }


    @Override
    public void onResume() {
        if (currentState instanceof LifeCycleAware) {
            ((LifeCycleAware) currentState).onResume();
        }
    }

    @Override
    public void onPause() {
        if (currentState instanceof LifeCycleAware) {
            ((LifeCycleAware) currentState).onPause();
        }
    }

    @Override
    public void onDestroy() {
        if (currentState instanceof LifeCycleAware) {
            ((LifeCycleAware) currentState).onDestroy();
        }
    }
}
