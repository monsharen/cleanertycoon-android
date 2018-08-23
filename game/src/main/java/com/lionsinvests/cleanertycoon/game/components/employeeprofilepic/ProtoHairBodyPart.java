package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Color;
import android.graphics.Paint;

public class ProtoHairBodyPart extends BaseBodyPart {

    private final int color;

    ProtoHairBodyPart(PixelCanvas pixelCanvas, Paint paint) {
        super(pixelCanvas, paint);
        this.color = Color.rgb(255, 64, 255);
    }

    @Override
    public void draw() {
        paint.setColor(color);

        pixelCanvas.drawLine(5, 5, 6);
        pixelCanvas.drawLine(4, 6, 8);
        pixelCanvas.drawLine(4, 7, 2);
        pixelCanvas.drawBox(3, 8, 2, 5);
    }
}
