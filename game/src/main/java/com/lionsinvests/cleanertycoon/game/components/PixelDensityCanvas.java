package com.lionsinvests.cleanertycoon.game.components;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.TypedValue;

public class PixelDensityCanvas {

    private Resources resources;
    private Canvas canvas;

    private final float pixelSize;

    public PixelDensityCanvas(Resources resources, Canvas canvas, int pixelSize) {
        this.resources = resources;
        this.canvas = canvas;
        this.pixelSize = pixelSize; //convertToPixels(8); // Assuming total size is 64
    }

    public void drawPoint(int x, int y, @NonNull Paint paint) {
        drawPixel(x, y, paint);
    }

    public void drawLine(
            float startX,
            float startY,
            float length,
            @NonNull Paint paint) {

        canvas.drawRect(
                convertToPixels(startX),
                convertToPixels(startY),
                convertToPixels(startX + length),
                convertToPixels(startY) + pixelSize,
                paint);
    }

    private float convertToPixels(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    private float convertToDp(float pixel) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixel, resources.getDisplayMetrics());
    }
/*
    private float convertToDp(float pixels) {
        return pixels * getDensity();
    }
*/
    private void drawPixel(int x, int y, Paint paint) {
        canvas.drawRect(
                convertToPixels(x),
                convertToPixels(y),
                convertToPixels(x) + pixelSize,
                convertToPixels(y) + pixelSize,
                paint);
    }

    private float getDensity() {
        return resources.getDisplayMetrics().density;
    }
}
