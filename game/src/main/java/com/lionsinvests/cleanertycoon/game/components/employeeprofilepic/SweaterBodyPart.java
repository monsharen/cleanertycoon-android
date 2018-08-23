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
    public void draw(int x, int y) {
        paint.setColor(sweaterColor);

        pixelCanvas.drawLine(x + 4, y + 10,4);
        pixelCanvas.drawLine(x + 2, y + 11,8);
        pixelCanvas.drawLine(x + 1, y + 12,10);

        // main body
        pixelCanvas.drawBox(x + 3, y + 13, 6, 6);

        // left arm
        pixelCanvas.drawBox(x + 1, y + 13, 1, 6);

        // right arm
        pixelCanvas.drawBox(x + 10, y + 13, 1, 6);

        paint.setColor(skinColor);
        pixelCanvas.drawPixel(x + 1, y + 19);
        pixelCanvas.drawPixel(x + 10, y + 19);

    }
}
