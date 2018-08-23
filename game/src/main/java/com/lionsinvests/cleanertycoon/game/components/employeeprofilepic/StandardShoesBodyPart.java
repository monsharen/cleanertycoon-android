package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Paint;

public class StandardShoesBodyPart extends BaseBodyPart {

    private final int color;

    StandardShoesBodyPart(PixelCanvas pixelCanvas, Paint paint, int color) {
        super(pixelCanvas, paint);
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        paint.setColor(color);
        // left leg
        pixelCanvas.drawBox(x + 2, y + 28, 2, 1);

        // right leg
        pixelCanvas.drawBox(x + 8, y + 28, 3, 1);
    }
}
