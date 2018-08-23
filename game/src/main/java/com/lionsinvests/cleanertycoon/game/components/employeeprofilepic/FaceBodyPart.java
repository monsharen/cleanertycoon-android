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
    public void draw(int x, int y) {
        paint.setColor(color);
        pixelCanvas.drawLine(x + 4, y + 2, 5);
        pixelCanvas.drawLine(x + 3, y + 3, 6);
        pixelCanvas.drawLine(x + 3, y + 4, 6);
        pixelCanvas.drawLine(x + 3, y + 5, 6);
        pixelCanvas.drawLine(x + 3, y + 6, 6);
        pixelCanvas.drawLine(x + 3, y + 7, 6);
        pixelCanvas.drawLine(x + 4, y + 8, 5);
        pixelCanvas.drawLine(x + 4, y + 9, 4);

        paint.setColor(Color.BLACK);
        pixelCanvas.drawPixel(x + 5, y + 5);
        pixelCanvas.drawPixel(x + 7, y + 5);
    }
}
