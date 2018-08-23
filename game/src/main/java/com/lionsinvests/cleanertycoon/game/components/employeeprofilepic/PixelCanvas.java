package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Canvas;
import android.graphics.Paint;

class PixelCanvas {

    private final Paint paint;
    private final float brushSize;

    private Canvas canvas;

    PixelCanvas(Paint paint, float brushSize) {
        this.paint = paint;
        this.brushSize = brushSize;
    }

    void onDraw(Canvas canvas) {
        this.canvas = canvas;
    }

    void drawPixel(float x, float y) {

        float left = x * brushSize;
        float top = y * brushSize;
        float right = (x * brushSize) + brushSize;
        float bottom = (y * brushSize) + brushSize;

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
        float left = startX * brushSize;
        float top = startY * brushSize;
        float right = (startX + length) * brushSize;
        float bottom = (startY + height) * brushSize;

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

        float left = startX * brushSize;
        float top = startY * brushSize;
        float right = (startX + length) * brushSize;
        float bottom = (startY * brushSize) + brushSize;

        canvas.drawRect(
                left,
                top,
                right,
                bottom,
                paint);
    }
}
