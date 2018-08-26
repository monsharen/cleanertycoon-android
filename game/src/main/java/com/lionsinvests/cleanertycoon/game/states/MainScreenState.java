package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import com.lionsinvests.cleanertycoon.game.*;
import com.lionsinvests.cleanertycoon.game.components.category.CategoriesComponent;
import com.lionsinvests.cleanertycoon.game.statemachine.*;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainScreenState implements State, LifeCycleAware {

    private RecyclerView.Adapter employeeListAdapter;
    private CategoriesComponent categoriesComponent;
    private EventListener eventListener;
    private Timer timer = null;
    private GameLogic gameLogic;
    private Activity activity;
    private Session session;

    @Override
    public void init(final Activity activity, Session session, final GameLogic gameLogic, final EventListener eventListener) {
        this.activity = activity;
        this.session = session;
        this.eventListener = eventListener;
        this.gameLogic = gameLogic;


        activity.setContentView(R.layout.activity_main);
        configureCategoryView();

        configurePlayerView(gameLogic.getPlayer());
        configureEmployeeListView(gameLogic.getPlayer().getCompany().getEmployees());
        configureTimePlayed(gameLogic);
        configureActionMenu();
        updatePlayPauseButton();
        configureTimer();

    }

    @Override
    public void end() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void configureTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    gameLogic.timeTick();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            redraw();
                        }
                    });
                } catch (OutOfFundsException e) {
                    eventListener.onEvent(StateId.GAME_OVER_OUT_OF_FUNDS);
                } catch (GameEventException e) {
                    eventListener.onEvent(StateId.GAME_EVENT);
                } catch (GameException e) {
                    Log.e(MainScreenState.class.getSimpleName(), "failed to perform turn" , e);
                }
            }
        }, 1000, 1000);
    }

    private void redraw() {
        Player player = gameLogic.getPlayer();

        TimePlayed timePlayed = gameLogic.getTimePlayed();
        TextView textView = activity.findViewById(R.id.playerWeeksPlayed);
        textView.setText(String.format(Locale.getDefault(), "Weeks: %d", timePlayed.getWeeks()));
        textView = activity.findViewById(R.id.playerDaysPlayed);
        textView.setText(String.format(Locale.getDefault(), "Days: %d", timePlayed.getDays() + 1));
        textView = activity.findViewById(R.id.playerYearsPlayed);
        textView.setText(String.format(Locale.getDefault(), "Years: %d", timePlayed.getYears()));
        textView = activity.findViewById(R.id.playerFunds);
        textView.setText(String.format(Locale.getDefault(), "Funds: $%.0f", player.getCompany().getFunds()));

        employeeListAdapter.notifyDataSetChanged();
        categoriesComponent.invalidate();
    }

    private void configureCategoryView() {
        categoriesComponent = activity.findViewById(R.id.categories);
        categoriesComponent.setCategoryCalculator(gameLogic.getCategoryCalculator());
    }

    private void configurePlayerView(Player player) {
        TextView textView = activity.findViewById(R.id.playerCompanyName);
        textView.setText(player.getCompany().getName());
        textView = activity.findViewById(R.id.playerFunds);
        textView.setText(String.format(Locale.getDefault(), "Funds: $%.0f", player.getCompany().getFunds()));
        RatingBar rating = activity.findViewById(R.id.playerRating);
        rating.setNumStars(player.getCompany().getRating());
    }

    private void configureEmployeeListView(List<Employee> employees) {
        RecyclerView employeeListRecyclerView = activity.findViewById(R.id.recycler_view);
        employeeListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        employeeListRecyclerView.setLayoutManager(mLayoutManager);

        EmployeeListOnClickListener employeeListOnClickListener = new EmployeeListOnClickListener(employees, employeeListRecyclerView);

        employeeListAdapter = new EmployeeListAdapter(employees, employeeListOnClickListener);
        employeeListRecyclerView.setAdapter(employeeListAdapter);
    }

    private void configureTimePlayed(final GameLogic gameLogic) {
        redraw();
        final Button button = activity.findViewById(R.id.playPauseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameLogic.getTimePlayed().togglePause();
                updatePlayPauseButton();
            }
        });
    }

    private void configureActionMenu() {

        final Button button = activity.findViewById(R.id.recruitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onEvent(StateId.RECRUITMENT);
            }
        });
    }

    @Override
    public void onResume() {
        if (employeeListAdapter != null) {
            employeeListAdapter.notifyDataSetChanged();
        }

        updatePlayPauseButton();
    }

    @Override
    public void onPause() {
        gameLogic.getTimePlayed().pause();
    }

    @Override
    public void onDestroy() {
        if (employeeListAdapter != null) {
            employeeListAdapter = null;
        }
    }

    private void updatePlayPauseButton() {
        final Button button = activity.findViewById(R.id.playPauseButton);
        if (gameLogic.getTimePlayed().isPaused()) {
            button.setText("Play");
        } else {
            button.setText("Pause");
        }
    }

    private class EmployeeListOnClickListener implements View.OnClickListener {

        private List<Employee> employees;
        private RecyclerView mRecyclerView;

        EmployeeListOnClickListener(List<Employee> employees, RecyclerView recyclerView) {
            this.employees = employees;
            this.mRecyclerView = recyclerView;
        }

        @Override
        public void onClick(View view) {
            int itemPosition = mRecyclerView.getChildLayoutPosition(view);
            Employee item = employees.get(itemPosition);
            Log.d("Test", "clicked on " + item.getName());
            session.put("employeeId", itemPosition);
            eventListener.onEvent(StateId.EMPLOYEE);
        }
    }
}
