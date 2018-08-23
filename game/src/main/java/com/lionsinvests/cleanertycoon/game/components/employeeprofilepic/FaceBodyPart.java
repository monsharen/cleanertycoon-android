package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Color;
import android.graphics.Paint;

public class FaceBodyPart extends BaseBodyPart {

    private final int color;

    FaceBodyPart(PixelCanvas pixelCanvas, Paint paint, int color) {
        super(pixelCanvas, paint);
        this.color = color;
    }

    @Override
    public void draw() {
        paint.setColor(color);
        pixelCanvas.drawLine(5, 5, 6);
        pixelCanvas.drawLine(4, 6, 7);
        pixelCanvas.drawBox(4, 7, 8, 4);
        pixelCanvas.drawBox(5, 11, 7, 3);
        pixelCanvas.drawLine(6, 14, 5);
        pixelCanvas.drawLine(6, 15, 4);

        paint.setColor(Color.BLACK);
        pixelCanvas.drawPixel(7, 9);
        pixelCanvas.drawPixel(10, 9);

        paint.setColor(Color.WHITE);
        pixelCanvas.drawLine(8, 12, 2);
    }
}
