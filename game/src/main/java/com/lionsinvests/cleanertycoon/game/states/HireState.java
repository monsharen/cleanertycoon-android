package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lionsinvests.cleanertycoon.game.*;
import com.lionsinvests.cleanertycoon.game.statemachine.EventListener;
import com.lionsinvests.cleanertycoon.game.statemachine.Session;
import com.lionsinvests.cleanertycoon.game.statemachine.State;
import com.lionsinvests.cleanertycoon.game.statemachine.StateId;

import java.util.List;

public class HireState implements State {

    private EventListener eventListener;

    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        this.eventListener = eventListener;
        activity.setContentView(R.layout.activity_hire);
        int itemId = session.getInteger("employeeId");
        List<Employee> employees = RecruitmentDatabase.getInstance().getEmployees();
        Employee employee = employees.get(itemId);
        configureProfile(activity, employee);
        configureHireMenu(activity, employee, gameLogic);
    }

    @Override
    public void end() {

    }

    private void configureProfile(Activity activity, Employee employee) {
        TextView view = activity.findViewById(R.id.name);
        view.setText(employee.getName());
        view = activity.findViewById(R.id.level);
        view.setText("5");
        //view = findViewById(R.id.happiness);
        //view.setText(employee.getHappiness());
    }

    private void configureHireMenu(final Activity activity, final Employee employee, final GameLogic gameLogic) {
        Button hireButton = activity.findViewById(R.id.hireButton);
        hireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player player = gameLogic.getPlayer();
                Company company = player.getCompany();
                company.getEmployees().add(employee);
                RecruitmentDatabase.getInstance().getEmployees().remove(employee);
                //activity.finish();
                eventListener.onEvent(StateId.PLAYING);
            }
        });
    }
}
