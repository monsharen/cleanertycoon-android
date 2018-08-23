package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.graphics.Paint;

public abstract class BaseBodyPart implements BodyPart {

    protected final PixelCanvas pixelCanvas;
    protected final Paint paint;

    BaseBodyPart(PixelCanvas pixelCanvas, Paint paint) {
        this.paint = paint;
        this.pixelCanvas = pixelCanvas;
    }

    public abstract void draw();
}
