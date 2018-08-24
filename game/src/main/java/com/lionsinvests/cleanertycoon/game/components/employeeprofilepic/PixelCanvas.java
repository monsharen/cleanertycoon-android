package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Canvas;
import android.graphics.Paint;

class PixelCanvas {

    private final int shiftX;
    private int shiftY;

    private final Paint paint;
    private final float brushSize;

    private Canvas canvas;

    PixelCanvas(Paint paint, float brushSize, int shiftX, int shiftY) {
        this.paint = paint;
        this.brushSize = brushSize;
        this.shiftX = shiftX;
        this.shiftY = shiftY;
    }

    void setShiftY(int y) {
        this.shiftY = y;
    }

    public int getShiftY() {
        return shiftY;
    }

    void onDraw(Canvas canvas) {
        this.canvas = canvas;
    }

    void drawPixel(float x, float y) {

        float left = (shiftX + x) * brushSize;
        float top = (shiftY + y) * brushSize;
        float right = ((shiftX + x) * brushSize) + brushSize;
        float bottom = ((shiftY + y) * brushSize) + brushSize;

        canvas.drawRect(
                left,
                top,
                right,
                bottom,
                paint);
    }

    void drawBox(float startX,
                        float startY,
                        float length,
                        float height) {
        float left = (shiftX + startX) * brushSize;
        float top = (shiftY + startY) * brushSize;
        float right = (shiftX + startX + length) * brushSize;
        float bottom = (shiftY + startY + height) * brushSize;

        canvas.drawRect(
                left,
                top,
                right,
                bottom,
                paint);
    }

    void drawLine(
            float startX,
            float startY,
            float length) {

        float left = (shiftX + startX) * brushSize;
        float top = (shiftY + startY) * brushSize;
        float right = (shiftX + startX + length) * brushSize;
        float bottom = ((shiftY + startY) * brushSize) + brushSize;

        canvas.drawRect(
                left,
                top,
                right,
                bottom,
                paint);
    }
}
