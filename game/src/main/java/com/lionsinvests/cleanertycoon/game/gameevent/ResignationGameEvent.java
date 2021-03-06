package com.lionsinvests.cleanertycoon.game.gameevent;

import com.lionsinvests.cleanertycoon.game.Employee;
import com.lionsinvests.cleanertycoon.game.GameEvent;
import com.lionsinvests.cleanertycoon.game.Player;

public class ResignationGameEvent implements GameEvent {

    private final Player player;
    private final Employee employee;

    public ResignationGameEvent(Player player, Employee employee) {
        this.player = player;
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

    @Override
    public void execute() {
        player.getCompany().getEmployees().remove(employee);
    }
}
