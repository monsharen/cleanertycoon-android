package com.lionsinvests.cleanertycoon.game;

public class Employee {

    private final String name;
    private int happiness = 100;
    private Contract contract = null;
    private final float salary;

    public Employee(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public float getSalary() {
        return salary;
    }
}
