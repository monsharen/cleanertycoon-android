package com.lionsinvests.cleanertycoon.game.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.statemachine.StateId;
import com.lionsinvests.cleanertycoon.game.statemachine.StateMachine;
import com.lionsinvests.cleanertycoon.game.statemachine.StateRegistry;

public class MainActivity extends AppCompatActivity {


    private StateMachine stateMachine;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameLogic = new GameLogic();
        StateRegistry stateRegistry = new StateRegistry();
        stateRegistry.register();
        stateMachine = new StateMachine(stateRegistry, this, gameLogic);

        startGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        stateMachine.changeState(StateId.PLAYING);
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
        stateMachine.onDestroy();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void startGame() {
        gameLogic.startNewGame();
        stateMachine.changeState(StateId.PLAYING);
    }
}
