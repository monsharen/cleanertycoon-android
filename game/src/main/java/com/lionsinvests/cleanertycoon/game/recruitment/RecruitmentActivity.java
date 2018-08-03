package com.lionsinvests.cleanertycoon.game.recruitment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lionsinvests.cleanertycoon.game.*;

import java.util.List;
import java.util.Random;

public class RecruitmentActivity extends AppCompatActivity {

    private RecyclerView.Adapter recruitList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recruitList != null) {
            recruitList.notifyDataSetChanged();
        } else {
            configureAvailableEmployees();
        }
    }

    private void configureAvailableEmployees() {
        RecyclerView employeeListRecyclerView = findViewById(R.id.availableEmployeesList);
        employeeListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        employeeListRecyclerView.setLayoutManager(mLayoutManager);

        List<Employee> employees = RecruitmentDatabase.getInstance().getEmployees();

        RecruitmentListOnClickListener employeeListOnClickListener = new RecruitmentListOnClickListener(employees, employeeListRecyclerView);

        recruitList = new EmployeeListAdapter(employees, employeeListOnClickListener);
        employeeListRecyclerView.setAdapter(recruitList);
    }

    private class RecruitmentListOnClickListener implements View.OnClickListener {

        private List<Employee> employees;
        private RecyclerView mRecyclerView;

        RecruitmentListOnClickListener(List<Employee> employees, RecyclerView recyclerView) {
            this.employees = employees;
            this.mRecyclerView = recyclerView;
        }

        @Override
        public void onClick(View view) {
            int itemPosition = mRecyclerView.getChildLayoutPosition(view);
            Employee item = employees.get(itemPosition);
            Log.d("Test", "clicked on " + item.getName());
            PlayerService.getInstance().getPlayer().getCompany().getEmployees().add(item);
            employees.remove(item);
        }
    }
}
