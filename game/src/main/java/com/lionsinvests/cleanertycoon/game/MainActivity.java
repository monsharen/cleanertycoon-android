package com.lionsinvests.cleanertycoon.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private Timer timer = null;
    private TimePlayed timePlayed;
    private boolean inBackground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Player player = new Player("CleanTech");
        timePlayed = new TimePlayed(player);

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Cheryl"));

        configurePlayerView(player);
        configureEmployeeListView(employees);
        configureTimePlayed();
        configureTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inBackground = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        inBackground = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
                if (!timePlayed.isPaused() && !inBackground) {
                    timePlayed.tick();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final TextView weeksPlayed = findViewById(R.id.playerWeeksPlayed);
                            final TextView yearsPlayed = findViewById(R.id.playerYearsPlayed);
                            weeksPlayed.setText(String.format(Locale.getDefault(), "Weeks: %.0f", timePlayed.getWeeks()));
                            yearsPlayed.setText(String.format(Locale.getDefault(), "Years: %.0f", timePlayed.getYears()));
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
        textView.setText(player.getCompanyName());
        textView = findViewById(R.id.playerFunds);
        textView.setText(String.format(Locale.getDefault(), "Funds: $%d", player.getFunds()));
        RatingBar rating = findViewById(R.id.playerRating);
        rating.setNumStars(player.getRating());
    }

    private void configureEmployeeListView(List<Employee> employees) {
        RecyclerView employeeListRecyclerView = findViewById(R.id.recycler_view);
        employeeListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        employeeListRecyclerView.setLayoutManager(mLayoutManager);

        EmployeeListOnClickListener employeeListOnClickListener = new EmployeeListOnClickListener(employees, employeeListRecyclerView);

        RecyclerView.Adapter mAdapter = new EmployeeListAdapter(employees, employeeListOnClickListener);
        employeeListRecyclerView.setAdapter(mAdapter);
    }

    private void startTimer() {
        final Button button = findViewById(R.id.playPauseButton);
        button.setText("Pause");
        timePlayed.start();
    }

    private void stopTimer() {
        final Button button = findViewById(R.id.playPauseButton);
        button.setText("Play");
        timePlayed.pause();
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
