package com.lionsinvests.cleanertycoon.game;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContractDatabase {

    private static ContractDatabase INSTANCE;

    private List<Contract> contracts = new ArrayList<>();

    private ContractDatabase() {

    }

    public static ContractDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContractDatabase();
        }

        return INSTANCE;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void refreshAvailableContracts() {
        contracts.clear();

        Random random = new Random();
        int numberOfRecruits = 10; //random.nextInt(8 - 1) + 1;
        Faker faker = new Faker();
        for (int i = 0; i < numberOfRecruits; i++) {
            float pay = 100 + random.nextFloat() * (5000f - 100f);
            float terminationFee = 50 + random.nextFloat() * (600f - 100f);
            String name = faker.commerce().department();
            Contract contract = new Contract(name, random.nextInt(), pay, 5, terminationFee, 1);
            contracts.add(contract);
        }
    }
}
