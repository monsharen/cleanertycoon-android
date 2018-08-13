package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lionsinvests.cleanertycoon.game.GameEvent;
import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.R;
import com.lionsinvests.cleanertycoon.game.statemachine.EventListener;
import com.lionsinvests.cleanertycoon.game.statemachine.Session;
import com.lionsinvests.cleanertycoon.game.statemachine.State;
import com.lionsinvests.cleanertycoon.game.statemachine.StateId;

import java.util.List;

public class GameEventState implements State {

    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        activity.setContentView(R.layout.activity_gameevent);

        GameEvent gameEvent = getGameEvent(gameLogic);
        configureScreen(gameEvent, activity, eventListener);
    }

    @Override
    public void end() {

    }

    private void configureScreen(GameEvent gameEvent, Activity activity, final EventListener eventListener) {
        TextView view = activity.findViewById(R.id.message);
        view.setText(gameEvent.getMessage());
        view = activity.findViewById(R.id.caption);
        view.setText(gameEvent.getCaption());

        Button button = activity.findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onEvent(StateId.MAIN_SCREEN);
            }
        });
    }

    private GameEvent getGameEvent(GameLogic gameLogic) {
        List<GameEvent> events = gameLogic.getEvents();
        GameEvent gameEvent = events.get(0);
        events.remove(gameEvent);
        return gameEvent;
    }
}
