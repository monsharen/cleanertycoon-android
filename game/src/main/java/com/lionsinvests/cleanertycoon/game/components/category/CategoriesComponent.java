package com.lionsinvests.cleanertycoon.game.components.category;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lionsinvests.cleanertycoon.game.Category;
import com.lionsinvests.cleanertycoon.game.CategoryCalculator;
import com.lionsinvests.cleanertycoon.game.R;
import com.lionsinvests.cleanertycoon.game.Rate;

public class CategoriesComponent extends LinearLayout {

    private CategoryCalculator categoryCalculator = new CategoryCalculator();

    public CategoriesComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        TextView textView = findViewById(R.id.industry);
        textView.setText(printPercentage(Category.INDUSTRY));

        textView = findViewById(R.id.home);
        textView.setText(printPercentage(Category.HOME));

        textView = findViewById(R.id.office);
        textView.setText(printPercentage(Category.OFFICE));

        textView = findViewById(R.id.government);
        textView.setText(printPercentage(Category.GOVERNMENT));

        textView = findViewById(R.id.street);
        textView.setText(printPercentage(Category.STREET));

        Log.d("test", "redrawing percentage");
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.category_rate, this);

        TextView textView = findViewById(R.id.industry);
        textView.setText(printPercentage(Category.INDUSTRY));

        textView = findViewById(R.id.home);
        textView.setText(printPercentage(Category.HOME));

        textView = findViewById(R.id.office);
        textView.setText(printPercentage(Category.OFFICE));

        textView = findViewById(R.id.government);
        textView.setText(printPercentage(Category.GOVERNMENT));

        textView = findViewById(R.id.street);
        textView.setText(printPercentage(Category.STREET));
    }

    public void setCategoryCalculator(CategoryCalculator categoryCalculator) {
        this.categoryCalculator = categoryCalculator;
    }

    private String printPercentage(Category category) {
        Rate rate = categoryCalculator.getRate(category);
        float percentage = toPercentage(rate);
        Log.d("test", "percentage: " + percentage);
        return String.format("%.0f%%", percentage);
    }

    private float toPercentage(Rate rate) {
        float currentRate = rate.getRate();
        float total = 1;
        return (100 * currentRate) / total;
    }

}
