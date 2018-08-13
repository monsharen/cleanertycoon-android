package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    private GameLogic gameLogic;

    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        activity.setContentView(R.layout.activity_gameevent);
        this.gameLogic = gameLogic;

        GameEvent gameEvent = getNextGameEvent();
        configureScreen(gameEvent, activity, eventListener);
        gameEvent.execute();
    }

    @Override
    public void end() {

    }

    private void configureScreen(final GameEvent gameEvent, Activity activity, final EventListener eventListener) {
        TextView view = activity.findViewById(R.id.message);
        view.setText(gameEvent.getMessage());
        view = activity.findViewById(R.id.caption);
        view.setText(gameEvent.getCaption());

        ImageView imageView = activity.findViewById(R.id.icon);
        imageView.setImageResource(getIconResourceId(gameEvent, activity));

        Button button = activity.findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gameLogic.getEvents().size() == 0) {
                    eventListener.onEvent(StateId.MAIN_SCREEN);
                } else {
                    eventListener.onEvent(StateId.GAME_EVENT);
                }
            }
        });
    }

    private int getIconResourceId(GameEvent gameEvent, Activity activity) {
        return activity.getResources().getIdentifier("com.lionsinvests.cleanertycoon:drawable/" + gameEvent.getIcon(), null, null);
    }

    private GameEvent getNextGameEvent() {
        List<GameEvent> events = gameLogic.getEvents();
        GameEvent gameEvent = events.get(0);
        events.remove(gameEvent);
        return gameEvent;
    }
}
