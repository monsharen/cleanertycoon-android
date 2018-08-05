package com.lionsinvests.cleanertycoon.game.statemachine;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private Map<String, Object> values = new HashMap<>();

    public void put(String key, Object value) {
        values.put(key, value);
    }

    public String getString(String key) {
        return (String) values.get(key);
    }

    public Integer getInteger(String key) {
        return (Integer) values.get(key);
    }

}
