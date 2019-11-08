package com.example.view1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PieChart extends View {
    private static final  float RADIUS = Utils.dp2px(150);
    private static final int[] ANGLES = {60,100,120,80};
    private static final int[] COLORS = {Color.parseColor("#448AFF"),
            Color.parseColor("#9575cd"),
            Color.parseColor("#ff8f00"),
            Color.parseColor("#00c853")};
    private static final int OFFSE_INDEX = 2;
    private static final float OFFSE_LENGTH = Utils.dp2px(20);

    private RectF mBounds = new RectF();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int currentAngle = 0;

    public PieChart(Context context,
            @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBounds.set(getWidth()/2 - RADIUS,getHeight()/2 - RADIUS,
                getWidth()/2 + RADIUS,getHeight()/2 + RADIUS);
        currentAngle = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0;i < ANGLES.length;i++){
            if(i == OFFSE_INDEX){
                canvas.save();
                canvas.translate(OFFSE_LENGTH *(float) Math.cos(Math.toRadians(currentAngle + ANGLES[i]/2)),
                        OFFSE_LENGTH *(float) Math.sin(Math.toRadians(currentAngle + ANGLES[i]/2))
                        );
            }

            mPaint.setColor(COLORS[i]);
            canvas.drawArc(mBounds,currentAngle,ANGLES[i],true, mPaint);
            currentAngle +=ANGLES[i];
            if(i == OFFSE_INDEX){
                canvas.restore();
            }
        }
    }
}
