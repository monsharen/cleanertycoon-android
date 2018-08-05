package com.lionsinvests.cleanertycoon.game.recruitment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lionsinvests.cleanertycoon.game.*;

import java.util.List;

public class HireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);
        Employee employee = getEmployee();
        configureHireMenu(employee);
        configureProfile(employee);
    }

    private Employee getEmployee() {
        int itemId = getIntent().getIntExtra("itemId", 0);
        List<Employee> employees = RecruitmentDatabase.getInstance().getEmployees();
        return employees.get(itemId);
    }

    private void configureProfile(Employee employee) {
        TextView view = findViewById(R.id.name);
        view.setText(employee.getName());
        view = findViewById(R.id.level);
        view.setText("5");
        //view = findViewById(R.id.happiness);
        //view.setText(employee.getHappiness());
    }

    private void configureHireMenu(final Employee employee) {
        Button hireButton = findViewById(R.id.hireButton);
        hireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerService playerService = PlayerService.getInstance();
                Company company = playerService.getPlayer().getCompany();
                company.getEmployees().add(employee);
                RecruitmentDatabase.getInstance().getEmployees().remove(employee);
                finish();
            }
        });
    }
}
