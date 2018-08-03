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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer = null;
    private TimePlayed timePlayed;
    private boolean inBackground = false;

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

    private Player loadPlayer() {
        Company company = new Company("CleanTech", 10000);
        Player player = new Player(company);
        timePlayed = new TimePlayed(player);
        PlayerService.getInstance().setPlayer(player);
        return player;
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
