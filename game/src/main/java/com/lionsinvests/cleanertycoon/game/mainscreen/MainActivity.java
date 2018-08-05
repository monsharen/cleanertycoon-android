package com.lionsinvests.cleanertycoon.game.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lionsinvests.cleanertycoon.game.*;
import com.lionsinvests.cleanertycoon.game.recruitment.RecruitmentActivity;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer = null;
    private boolean inBackground = false;
    private RecyclerView.Adapter employeeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecruitmentDatabase.getInstance().refreshAvailableRecruits();

        Player player = loadPlayer();

        configurePlayerView(player);
        configureEmployeeListView(player.getCompany().getEmployees());
        configureTimePlayed();
        configureTimer();
        configureActionMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inBackground = false;

        Log.d("test", "onResume");
        if (employeeListAdapter != null) {
            employeeListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        inBackground = true;

        if (employeeListAdapter != null) {
            employeeListAdapter = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private Player loadPlayer() {
        GameLogic.getInstance().startNewGame();
        return GameLogic.getInstance().getPlayer();
    }

    private void configureActionMenu() {
        final Intent intent = new Intent(this, RecruitmentActivity.class);

        final Button button = findViewById(R.id.recruitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void configureTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!inBackground) {
                    GameLogic.getInstance().timeTick();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Player player = GameLogic.getInstance().getPlayer();

                            TimePlayed timePlayed = GameLogic.getInstance().getTimePlayed();
                            TextView textView = findViewById(R.id.playerWeeksPlayed);
                            textView.setText(String.format(Locale.getDefault(), "Weeks: %d", timePlayed.getWeeks()));
                            textView = findViewById(R.id.playerYearsPlayed);
                            textView.setText(String.format(Locale.getDefault(), "Years: %d", timePlayed.getYears()));
                            textView = findViewById(R.id.playerFunds);
                            textView.setText(String.format(Locale.getDefault(), "Funds: $%.0f", player.getCompany().getFunds()));
                        }
                    });
                }

            }
        }, 1000, 1000);
    }

    private void configureTimePlayed() {
        stopTimer();
        final Button button = findViewById(R.id.playPauseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePlayed timePlayed = GameLogic.getInstance().getTimePlayed();
                if (timePlayed.isPaused()) {
                    startTimer();
                } else {
                    stopTimer();
                }
            }
        });
    }

    private void configurePlayerView(Player player) {
        TextView textView = findViewById(R.id.playerCompanyName);
        textView.setText(player.getCompany().getName());
        textView = findViewById(R.id.playerFunds);
        textView.setText(String.format(Locale.getDefault(), "Funds: $%.0f", player.getCompany().getFunds()));
        RatingBar rating = findViewById(R.id.playerRating);
        rating.setNumStars(player.getCompany().getRating());
    }

    private void configureEmployeeListView(List<Employee> employees) {
        RecyclerView employeeListRecyclerView = findViewById(R.id.recycler_view);
        employeeListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        employeeListRecyclerView.setLayoutManager(mLayoutManager);

        EmployeeListOnClickListener employeeListOnClickListener = new EmployeeListOnClickListener(employees, employeeListRecyclerView);

        employeeListAdapter = new EmployeeListAdapter(employees, employeeListOnClickListener);
        employeeListRecyclerView.setAdapter(employeeListAdapter);
    }

    private void startTimer() {
        final Button button = findViewById(R.id.playPauseButton);
        button.setText("Pause");
        GameLogic.getInstance().getTimePlayed().start();
    }

    private void stopTimer() {
        final Button button = findViewById(R.id.playPauseButton);
        button.setText("Play");
        GameLogic.getInstance().getTimePlayed().pause();
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
        }
    }
}
