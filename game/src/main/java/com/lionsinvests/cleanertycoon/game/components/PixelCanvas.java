package com.lionsinvests.cleanertycoon.game.components;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PixelCanvas {

    private final Paint paint;
    private final float brushSize;

    private Canvas canvas;

    PixelCanvas(Paint paint, float brushSize) {
        this.paint = paint;
        this.brushSize = brushSize;
    }

    public void onDraw(Canvas canvas) {
        this.canvas = canvas;
    }

    public void drawPixel(float x, float y) {

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

    public void drawBox(float startX,
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

    public void drawLine(
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
