package com.lionsinvests.cleanertycoon.game.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.statemachine.StateMachine;
import com.lionsinvests.cleanertycoon.game.statemachine.StateRegistry;

public class MainActivity extends AppCompatActivity {

    private StateMachine stateMachine = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameLogic gameLogic = new GameLogic();
        StateRegistry stateRegistry = new StateRegistry();
        stateRegistry.register();
        stateMachine = new StateMachine(stateRegistry, this, gameLogic);
    }

    @Override
    protected void onResume() {
        super.onResume();
        stateMachine.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stateMachine.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (stateMachine != null) {
            stateMachine.onDestroy();
            stateMachine = null;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
