package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Color;
import android.graphics.Paint;

public class CowboyHatBodyPart extends BaseBodyPart {

    private final int color;

    CowboyHatBodyPart(PixelCanvas pixelCanvas, Paint paint) {
        super(pixelCanvas, paint);
        this.color = Color.LTGRAY;
    }

    @Override
    public void draw() {
        paint.setColor(color);

        pixelCanvas.drawLine(8, 1, 2);
        pixelCanvas.drawBox(5, 2, 6, 4);
        pixelCanvas.drawLine(1, 5, 14);
        pixelCanvas.drawPixel(0, 4);
        pixelCanvas.drawPixel(0, 5);
        pixelCanvas.drawPixel(15, 4);
        pixelCanvas.drawPixel(15, 5);
    }
}
