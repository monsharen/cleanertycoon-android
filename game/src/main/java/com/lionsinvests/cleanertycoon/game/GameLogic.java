package com.lionsinvests.cleanertycoon.game;

import android.util.Log;

import com.lionsinvests.cleanertycoon.game.gameevent.ResignationGameEvent;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private List<GameEvent> events;
    private TimePlayed timePlayed;
    private Player player;

    public GameLogic() {
        events = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public synchronized TimePlayed getTimePlayed() {
        return timePlayed;
    }

    public List<GameEvent> getEvents() {
        return events;
    }

    public void timeTick() throws GameException {
        if (!timePlayed.isPaused()) {

            if (events.size() != 0) {
                throw new GameEventException();
            }

            timePlayed.tick();
            Log.d(GameLogic.class.getSimpleName(), "tick: " + timePlayed.isPaused() + ", " + timePlayed.getDays() + "/" + timePlayed.getWeeks() + "/" + timePlayed.getYears());

            if (timePlayed.getDays() == 0) {
                deductSalaries();
                deductHappiness();
            }

            if (player.getCompany().getFunds() < 0) {
                throw new OutOfFundsException();
            }
        }
    }

    public void startNewGame() {
        RecruitmentDatabase.getInstance().refreshAvailableRecruits();
        ContractDatabase.getInstance().refreshAvailableContracts();
        Company company = new Company("CleanTech", 10000);
        player = new Player(company);
        timePlayed = new TimePlayed(player);
        events = new ArrayList<>();
    }

    private void deductHappiness() {
        Company company = player.getCompany();
        List<Employee> employees = company.getEmployees();

        for (Employee employee : employees) {
            Contract contract = employee.getContract();
            if (contract != null) {
                int happinessDeduction = contract.getHappiness();
                employee.setHappiness(employee.getHappiness() - happinessDeduction);

                if (employee.getHappiness() <= 0) {
                    events.add(new ResignationGameEvent(player, employee));
                }
            }
        }
    }

    private void deductSalaries() {
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
