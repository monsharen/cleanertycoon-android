package com.lionsinvests.cleanertycoon.game.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class EmployeeProfileView extends View {

    private Paint paint;

    public EmployeeProfileView(Context context) {
        super(context);
        init();
    }

    public EmployeeProfileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmployeeProfileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        PixelDensityCanvas pixelDensityCanvas = new PixelDensityCanvas(getResources(), canvas, 2);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        drawFace(pixelDensityCanvas);

        paint.setColor(Color.BLUE);
        drawBody(pixelDensityCanvas);
    }

    private void drawFace(PixelDensityCanvas canvas) {
        drawLine(canvas, 4, 5, 2);
        drawLine(canvas, 3, 6, 3);
        drawLine(canvas, 3, 6, 4);
        drawLine(canvas, 3, 6, 5);
        drawLine(canvas, 3, 6, 6);
        drawLine(canvas, 3, 6, 7);
        drawLine(canvas, 4, 5, 8);
        drawLine(canvas, 4, 4, 9);
    }

    private void drawBody(PixelDensityCanvas canvas) {
        drawLine(canvas, 4, 4, 10);
        drawLine(canvas, 1, 8, 11);
        drawLine(canvas, 0, 9, 12);

    }

    private void drawLine(PixelDensityCanvas canvas, int fromX, int length, int y) {
        //int endX = fromX + length;
        /* for (int x = fromX; x < endX; x++) {
            canvas.drawPoint(x, y, paint);
        } */
        canvas.drawLine(fromX, y, length, paint);
    }
}
