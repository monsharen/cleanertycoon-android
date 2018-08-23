package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Paint;

public class SweaterBodyPart extends BaseBodyPart {

    private final int sweaterColor;
    private final int skinColor;

    SweaterBodyPart(PixelCanvas pixelCanvas, Paint paint, int sweaterColor, int skinColor) {
        super(pixelCanvas, paint);
        this.sweaterColor = sweaterColor;
        this.skinColor = skinColor;
    }

    @Override
    public void draw() {
        paint.setColor(sweaterColor);

        pixelCanvas.drawLine(6, 0,4);
        pixelCanvas.drawBox(3, 1, 9, 2);
        pixelCanvas.drawBox(4, 2, 7, 14);

        // left arm
        pixelCanvas.drawBox(2, 2, 1, 12);


        // right arm
        pixelCanvas.drawBox(12, 2, 1, 12);

        paint.setColor(skinColor);
        pixelCanvas.drawBox(2, 14, 1, 2);
        pixelCanvas.drawBox(12, 14, 1, 2);
/*
        pixelCanvas.drawLine(2, 1,8);
        pixelCanvas.drawLine(1, 2,10);

        // main body
        pixelCanvas.drawBox(3, 3, 6, 6);

        // left arm
        pixelCanvas.drawBox(1, 3, 1, 6);

        // right arm
        pixelCanvas.drawBox(10, 3, 1, 6);

        paint.setColor(skinColor);
        pixelCanvas.drawPixel(1, 8);
        pixelCanvas.drawPixel(10, 8);
        */
    }
}
