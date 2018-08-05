package com.lionsinvests.cleanertycoon.game;

import android.util.Log;

import java.util.List;

public class GameLogic {

    private static GameLogic INSTANCE = null;

    private TimePlayed timePlayed;
    private Player player;

    private GameLogic() {

    }

    public static GameLogic getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameLogic();
        }
        return INSTANCE;
    }

    public Player getPlayer() {
        return player;
    }

    public synchronized TimePlayed getTimePlayed() {
        return timePlayed;
    }

    public void timeTick() {
        if (!timePlayed.isPaused()) {
            timePlayed.tick();
            Log.d(GameLogic.class.getSimpleName(), "tick: " + timePlayed.isPaused() + ", " + timePlayed.getDays() + "/" + timePlayed.getWeeks() + "/" + timePlayed.getYears());

            if (timePlayed.getDays() == 0) {
                GameLogic.getInstance().deductSalaries();
            }
        }
    }

    public void startNewGame() {
        Company company = new Company("CleanTech", 10000);
        player = new Player(company);
        timePlayed = new TimePlayed(player);
    }

    public void deductSalaries() {
        Company company = player.getCompany();
        List<Employee> employees = company.getEmployees();

        float money = 0f;
        for (Employee employee : employees) {
            float salary = employee.getSalary();
            money += salary;
        }
        Log.d("test", "will deduct " + money + " from company. Funds: " + company.getFunds());
        company.setFunds(company.getFunds() - money);
    }
}
