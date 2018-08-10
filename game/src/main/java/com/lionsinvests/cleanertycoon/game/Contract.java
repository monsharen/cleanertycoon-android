package com.lionsinvests.cleanertycoon.game;

public class Contract {

    private final String name;
    private final int length;
    private final float pay;
    private final int happiness;
    private final float terminationFee;
    private final int level;

    public Contract(String name, int length, float pay, int happiness, float terminationFee, int level) {
        this.name = name;
        this.length = length;
        this.pay = pay;
        this.happiness = happiness;
        this.terminationFee = terminationFee;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public float getPay() {
        return pay;
    }

    public int getHappiness() {
        return happiness;
    }

    public float getTerminationFee() {
        return terminationFee;
    }

    public int getLevel() {
        return level;
    }
}
