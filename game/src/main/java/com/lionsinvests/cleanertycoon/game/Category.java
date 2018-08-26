package com.lionsinvests.cleanertycoon.game;

public enum Category {
    INDUSTRY("Industry"),
    OFFICE("Office"),
    HOME("Home"),
    GOVERNMENT("Government"),
    STREET("Street");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
