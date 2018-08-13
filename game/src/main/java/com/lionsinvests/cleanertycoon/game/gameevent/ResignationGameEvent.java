package com.lionsinvests.cleanertycoon.game.gameevent;

import com.lionsinvests.cleanertycoon.game.Employee;
import com.lionsinvests.cleanertycoon.game.GameEvent;

public class ResignationGameEvent implements GameEvent {

    private final Employee employee;

    public ResignationGameEvent(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String getCaption() {
        return "Resignation!";
    }

    @Override
    public String getMessage() {
        return employee.getName() + " has resigned due to being unhappy";
    }

    @Override
    public GameEventIcon getIcon() {
        return GameEventIcon.ANGRY;
    }
}
