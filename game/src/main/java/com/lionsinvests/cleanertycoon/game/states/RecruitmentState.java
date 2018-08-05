package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.lionsinvests.cleanertycoon.game.*;
import com.lionsinvests.cleanertycoon.game.statemachine.*;

import java.util.List;

public class RecruitmentState implements State, LifeCycleAware {

    private RecyclerView.Adapter recruitList = null;
    private Activity activity;
    private Session session;
    private EventListener eventListener;

    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        activity.setContentView(R.layout.activity_recruitment);
        this.activity = activity;
        this.session = session;
        this.eventListener = eventListener;

        configureAvailableEmployees();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end() {

    }

    @Override
    public void onResume() {
        recruitList.notifyDataSetChanged();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    private void configureAvailableEmployees() {
        RecyclerView employeeListRecyclerView = activity.findViewById(R.id.availableEmployeesList);
        employeeListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
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
            //final Intent intent = new Intent(RecruitmentActivity.this, HireActivity.class);
            //intent.putExtra("itemPosition", itemPosition);
            session.put("employeeId", itemPosition);
            eventListener.onEvent(StateId.HIRE);

        }
    }
}
