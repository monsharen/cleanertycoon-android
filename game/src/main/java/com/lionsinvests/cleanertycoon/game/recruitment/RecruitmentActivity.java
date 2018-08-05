package com.lionsinvests.cleanertycoon.game.recruitment;

import android.content.Intent;
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

        RecruitmentListOnClickListener employeeListOnClickListener = new RecruitmentListOnClickListener(employeeListRecyclerView);

        recruitList = new EmployeeListAdapter(employees, employeeListOnClickListener);
        employeeListRecyclerView.setAdapter(recruitList);
    }

    private class RecruitmentListOnClickListener implements View.OnClickListener {

        private RecyclerView mRecyclerView;

        RecruitmentListOnClickListener(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        @Override
        public void onClick(View view) {
            int itemPosition = mRecyclerView.getChildLayoutPosition(view);
            final Intent intent = new Intent(RecruitmentActivity.this, HireActivity.class);
            intent.putExtra("itemPosition", itemPosition);
            startActivity(intent);
        }
    }
}
