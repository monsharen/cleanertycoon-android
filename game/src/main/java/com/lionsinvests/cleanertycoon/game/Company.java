package com.lionsinvests.cleanertycoon.game;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private final String name;
    private float funds;
    private int rating = 1;
    private List<Employee> employees = new ArrayList<>();

    public Company(String name, float initialFunds) {
        this.name = name;
        this.funds = initialFunds;
    }

    public String getName() {
        return name;
    }

    public float getFunds() {
        return funds;
    }

    public void setFunds(float funds) {
        this.funds = funds;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
