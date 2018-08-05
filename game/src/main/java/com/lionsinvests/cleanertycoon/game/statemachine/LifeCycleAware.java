package com.lionsinvests.cleanertycoon.game.statemachine;

public interface LifeCycleAware {

    void onResume();
    void onPause();
    void onDestroy();
}
