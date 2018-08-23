package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Paint;

public class TrousersBodyPart extends BaseBodyPart {

    private final int color;

    TrousersBodyPart(PixelCanvas pixelCanvas, Paint paint, int color) {
        super(pixelCanvas, paint);
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        paint.setColor(color);

        // top part
        pixelCanvas.drawBox(x + 3, y + 19, 6, 2);

        // left leg
        pixelCanvas.drawBox(x + 3, y + 21, 1, 7);

        // right leg
        pixelCanvas.drawBox(x + 8, y + 21, 1, 7);
    }
}
