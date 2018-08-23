package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Paint;

public class TrousersBodyPart extends BaseBodyPart {

    private final int color;

    TrousersBodyPart(PixelCanvas pixelCanvas, Paint paint, int color) {
        super(pixelCanvas, paint);
        this.color = color;
    }

    @Override
    public void draw() {
        paint.setColor(color);

        // top part
        pixelCanvas.drawBox(4, 0, 7, 2);

        // left leg
        pixelCanvas.drawBox(4, 2, 1, 11);

        // right leg
        pixelCanvas.drawBox(10, 2, 1, 11);
    }
}
