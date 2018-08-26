package com.lionsinvests.cleanertycoon.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CategoryCalculator {

    private final Map<Category, Rate> categoryRates = new HashMap<>(5);

    public CategoryCalculator() {
        categoryRates.put(Category.INDUSTRY, new Rate(1.0f));
        categoryRates.put(Category.HOME, new Rate(1.0f));
        categoryRates.put(Category.STREET, new Rate(1.0f));
        categoryRates.put(Category.OFFICE, new Rate(1.0f));
        categoryRates.put(Category.GOVERNMENT, new Rate(1.0f));
    }

    public void recalculateCategoryRates() {
        Random random = new Random();

        for (Map.Entry<Category, Rate> entry : categoryRates.entrySet()) {
            Category category = entry.getKey();
            float newRate = 0.1f + random.nextFloat() * (2 - 0.1f);
            categoryRates.put(category, new Rate(newRate));
        }
    }

    public Rate getRate(Category category) {
        return categoryRates.get(category);
    }
}
