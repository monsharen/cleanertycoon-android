package com.lionsinvests.cleanertycoon.game;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void refreshAvailableRecruits() {
        employees.clear();

        Random random = new Random();
        int numberOfRecruits = 10; //random.nextInt(8 - 1) + 1;
        Faker faker = new Faker();
        for (int i = 0; i < numberOfRecruits; i++) {
            float salary = 100 + random.nextFloat() * (500f - 100f);
            String name = faker.name().fullName();
            Employee employee = new Employee(name, salary);
            employees.add(employee);
        }
    }
}
