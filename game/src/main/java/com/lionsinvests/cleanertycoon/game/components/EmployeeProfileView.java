package com.lionsinvests.cleanertycoon.game.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class EmployeeProfileView extends android.support.v7.widget.AppCompatImageView {

    private static int BRUSH_SIZE_DP = 3;

    private PixelCanvas pixelCanvas;

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
        float brushSize =  getBrushSize();
        pixelCanvas = new PixelCanvas(paint, brushSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pixelCanvas.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        drawFace(Color.YELLOW);
        drawBody(Color.BLUE);
        drawTrousers(Color.GREEN);
        drawFeet(Color.RED);
    }

    private void drawFace(int color) {
        paint.setColor(color);
        drawLine(4, 5, 2);
        drawLine(3, 6, 3);
        drawLine(3, 6, 4);
        drawLine(3, 6, 5);
        drawLine(3, 6, 6);
        drawLine(3, 6, 7);
        drawLine(4, 5, 8);
        drawLine(4, 4, 9);

        paint.setColor(Color.BLACK);
        pixelCanvas.drawPixel(5, 5);
        pixelCanvas.drawPixel(7, 5);
    }

    private void drawBody(int color) {
        paint.setColor(color);
        drawLine(4, 4, 10);
        drawLine(2, 8, 11);
        drawLine(1, 10, 12);

        // main body
        pixelCanvas.drawBox(3, 13, 6, 6);

        // left arm
        pixelCanvas.drawBox(1, 13, 1, 6);

        // right arm
        pixelCanvas.drawBox(10, 13, 1, 6);
    }

    private void drawTrousers(int color) {
        paint.setColor(color);

        // top part
        pixelCanvas.drawBox(3, 19, 6, 2);

        // left leg
        pixelCanvas.drawBox(3, 21, 1, 7);

        // right leg
        pixelCanvas.drawBox(8, 21, 1, 7);
    }

    private void drawFeet(int color) {
        paint.setColor(color);
        // left leg
        pixelCanvas.drawBox(2, 28, 2, 1);

        // right leg
        pixelCanvas.drawBox(8, 28, 3, 1);
    }

    private void drawLine(int fromX, int length, int y) {
        pixelCanvas.drawLine(fromX, y, length);
    }

    private float getBrushSize() {
        return (BRUSH_SIZE_DP * getResources().getDisplayMetrics().density);
    }
}
