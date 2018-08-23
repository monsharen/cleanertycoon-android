package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Paint;

public class StandardShoesBodyPart extends BaseBodyPart {

    private final int color;

    StandardShoesBodyPart(PixelCanvas pixelCanvas, Paint paint, int color) {
        super(pixelCanvas, paint);
        this.color = color;
    }

    @Override
    public void draw() {
        paint.setColor(color);
        // left leg
        pixelCanvas.drawPixel(4, 13);
        pixelCanvas.drawLine(3, 14, 2);
        pixelCanvas.drawLine(2, 15, 3);

        // right leg
        pixelCanvas.drawPixel(10, 13);
        pixelCanvas.drawLine(10, 14, 3);
        pixelCanvas.drawPixel(10, 15);
        pixelCanvas.drawLine(12, 15, 2);
    }
}
