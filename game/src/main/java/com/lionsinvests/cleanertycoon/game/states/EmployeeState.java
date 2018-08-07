package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lionsinvests.cleanertycoon.game.Employee;
import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.R;
import com.lionsinvests.cleanertycoon.game.statemachine.EventListener;
import com.lionsinvests.cleanertycoon.game.statemachine.Session;
import com.lionsinvests.cleanertycoon.game.statemachine.State;
import com.lionsinvests.cleanertycoon.game.statemachine.StateId;

public class EmployeeState implements State {

    private Activity activity;
    private EventListener eventListener;

    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        this.eventListener = eventListener;
        this.activity = activity;
        activity.setContentView(R.layout.activity_employee);
        Integer employeeId = session.getInteger("employeeId");
        Employee employee = gameLogic.getPlayer().getCompany().getEmployees().get(employeeId);
        configureProfile(employee);
        configureMenu();
    }

    @Override
    public void end() {

    }

    private void configureProfile(Employee employee) {
        TextView view = activity.findViewById(R.id.name);
        view.setText(employee.getName());
        view = activity.findViewById(R.id.level);
        view.setText("5");
        //view = findViewById(R.id.happiness);
        //view.setText(employee.getHappiness());
    }

    private void configureMenu() {
        Button view = activity.findViewById(R.id.backButton);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onEvent(StateId.MAIN_SCREEN);
            }
        });
    }
}
