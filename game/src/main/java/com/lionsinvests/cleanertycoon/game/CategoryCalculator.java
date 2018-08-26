package com.lionsinvests.cleanertycoon.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CategoryCalculator {

    private final Map<Category, Rate> categoryRates = new HashMap<>(5);

    public CategoryCalculator() {
        categoryRates.put(new Category("Industry"), new Rate(1.0f));
        categoryRates.put(new Category("Home"), new Rate(1.0f));
        categoryRates.put(new Category("Office"), new Rate(1.0f));
        categoryRates.put(new Category("Government"), new Rate(1.0f));
        categoryRates.put(new Category("Street"), new Rate(1.0f));
    }

    public void recalculateCategoryRates() {

        Random random = new Random();

        for (Map.Entry<Category, Rate> entry : categoryRates.entrySet()) {
            Category category = entry.getKey();
            float newRate = 0.1f + random.nextFloat() * (2 - 0.1f);
            categoryRates.put(category, new Rate(newRate));
        }
    }
}
