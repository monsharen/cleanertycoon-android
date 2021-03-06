package com.lionsinvests.cleanertycoon.game.components.employeeprofilepic;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

public class EmployeeProfileView extends android.support.v7.widget.AppCompatImageView {

    private static final int SKIN_COLOR = Color.rgb(255, 212, 120);

    private static int BRUSH_SIZE_DP = 2;

    private static int START_X = 15;
    private static int START_Y = 0;

    private PixelCanvas headPixelCanvas;
    private PixelCanvas bodyPixelCanvas;
    private PixelCanvas trouserPixelCanvas;

    private ValueAnimator animator = null;

    private Paint paint;

    private List<BodyPart> parts = new ArrayList<>();

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
        headPixelCanvas = new PixelCanvas(paint, brushSize, START_X, START_Y);
        bodyPixelCanvas = new PixelCanvas(paint, brushSize, START_X, START_Y + 16);
        trouserPixelCanvas = new PixelCanvas(paint, brushSize, START_X, START_Y + 32);

        parts.add(new FaceBodyPart(headPixelCanvas, paint, SKIN_COLOR));
        parts.add(new ProtoHairBodyPart(headPixelCanvas, paint));
        parts.add(new CowboyHatBodyPart(headPixelCanvas, paint));
        parts.add(new SweaterBodyPart(bodyPixelCanvas, paint, Color.BLUE, SKIN_COLOR));
        parts.add(new TrousersBodyPart(trouserPixelCanvas, paint, Color.CYAN));
        parts.add(new StandardShoesBodyPart(trouserPixelCanvas, paint, Color.DKGRAY));


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        headPixelCanvas.onDraw(canvas);
        bodyPixelCanvas.onDraw(canvas);
        trouserPixelCanvas.onDraw(canvas);

        for (BodyPart part : parts) {
           part.draw();
        }
    }

    private void startAnimation() {
        animator = ValueAnimator.ofInt(-3, 3);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                headPixelCanvas.setShiftY(headPixelCanvas.getShiftY() - (int) valueAnimator.getAnimatedValue());
                bodyPixelCanvas.setShiftY(bodyPixelCanvas.getShiftY() - (int) valueAnimator.getAnimatedValue());
                invalidate();
            }
        });
        animator.setInterpolator(new BounceInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(500);
        animator.start();
    }

    private float getBrushSize() {
        return (BRUSH_SIZE_DP * getResources().getDisplayMetrics().density);
    }
}
