package com.lionsinvests.cleanertycoon.game;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class RecruitmentDatabase {

    private static RecruitmentDatabase INSTANCE;

    private List<Employee> employees = new ArrayList<>();

    private RecruitmentDatabase() {

    }

    public static RecruitmentDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RecruitmentDatabase();
        }

        return INSTANCE;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void refreshAvailableRecruits(int numberOfRecruits) {
        employees.clear();

        for (int i = 0; i < numberOfRecruits; i++) {
            Faker faker = new Faker();
            String name = faker.name().fullName();
            Employee employee = new Employee(name);
            employees.add(employee);
        }
    }
}
