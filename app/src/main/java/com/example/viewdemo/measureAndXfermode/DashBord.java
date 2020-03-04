package com.example.viewdemo.measureAndXfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DashBord extends View {
    public static final float RADIUS = Utils.dp2px(150);
    public static final float OPEN_ANGLE = 120;
    public static final float STROKE_WIDTH = Utils.dp2px(3);
    public static final float POINTER_LENGTH = Utils.dp2px(100);
    Paint mPaint ;
    Path mDash;
    Path mArc;
    PathDashPathEffect mPathDashPathEffect;
    PathMeasure mPathMeasure;


    public DashBord(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mDash =  new Path();
        mArc = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(STROKE_WIDTH);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mArc.reset();
        mArc.addArc(getWidth() / 2f - RADIUS,getHeight() / 2f - RADIUS,
                getWidth() / 2f +RADIUS,getHeight() / 2f + RADIUS,
                90+OPEN_ANGLE/2,360-OPEN_ANGLE);
        mPathMeasure = new PathMeasure(mArc,false);
        mDash.reset();
        mDash.addRect(0,0,Utils.dp2px(2),Utils.dp2px(10), Path.Direction.CCW);
        mPathDashPathEffect = new PathDashPathEffect(mDash,(mPathMeasure.getLength()-Utils.dp2px(2))/20,0,PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mArc,mPaint);

        canvas.drawLine(getWidth() / 2f, getHeight() / 2f,
                getWidth() / 2f+POINTER_LENGTH * (float) Math.cos(getAngleFromMark(0)),
                getHeight() / 2f+POINTER_LENGTH * (float) Math.sin(getAngleFromMark(0)),
                                mPaint);

        mPaint.setPathEffect(mPathDashPathEffect);
        canvas.drawPath(mArc,mPaint);
    }

    private double getAngleFromMark(int mark) {
        return Math.toRadians(90+OPEN_ANGLE/2 + (360 - OPEN_ANGLE) / 20f * mark);
    }
}
