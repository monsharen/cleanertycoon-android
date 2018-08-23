package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class EmployeeProfileView extends android.support.v7.widget.AppCompatImageView {

    private static int BRUSH_SIZE_DP = 3;

    private static int START_X = 15;
    private static int START_Y = 5;

    private PixelCanvas pixelCanvas;

    private Paint paint;

    private FaceBodyPart faceBodyPart;
    private SweaterBodyPart sweaterBodyPart;
    private TrousersBodyPart trousersBodyPart;
    private StandardShoesBodyPart standardShoesBodyPart;

    public EmployeeProfileView(Context context) {
        super(context);
        init();
    }

    public EmployeeProfileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmployeeProfileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        float brushSize =  getBrushSize();
        pixelCanvas = new PixelCanvas(paint, brushSize);

        faceBodyPart = new FaceBodyPart(pixelCanvas, paint, Color.YELLOW);
        sweaterBodyPart = new SweaterBodyPart(pixelCanvas, paint, Color.BLUE, Color.YELLOW);
        trousersBodyPart = new TrousersBodyPart(pixelCanvas, paint, Color.CYAN);
        standardShoesBodyPart = new StandardShoesBodyPart(pixelCanvas, paint, Color.DKGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pixelCanvas.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);

        faceBodyPart.draw(START_X, START_Y);
        sweaterBodyPart.draw(START_X, START_Y);
        trousersBodyPart.draw(START_X, START_Y);
        standardShoesBodyPart.draw(START_X, START_Y);
    }

    private float getBrushSize() {
        return (BRUSH_SIZE_DP * getResources().getDisplayMetrics().density);
    }
}
