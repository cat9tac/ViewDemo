package com.example.viewdemo.measureAndXfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.viewdemo.R;

public class AvatarView extends View {
    private static final float WIDTH = Utils.dp2px(300);
    private static final float OFFSET = Utils.dp2px(50);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    RectF mBound = new RectF();

    public AvatarView(Context context,
            @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = getAvater((int) WIDTH);
        mBound.set(OFFSET, OFFSET, OFFSET + WIDTH, OFFSET + WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = WIDTH / 2F;
        canvas.drawCircle(OFFSET + radius, OFFSET + radius, radius + 10, mPaint);//画圆黑边
        radius = 60;
        canvas.drawCircle(OFFSET + radius, OFFSET + radius, radius + 10, mPaint);//画小圆黑边
        canvas.drawCircle(OFFSET + WIDTH - radius, OFFSET + radius, radius + 10, mPaint);//画小圆黑边

        canvas.saveLayer(mBound, mPaint);//获取一个矩形区域的离屏幕缓冲
        radius = WIDTH / 2F;
        canvas.drawCircle(OFFSET + radius, OFFSET + radius, radius, mPaint);//画圆
        radius = 60;
        canvas.drawCircle(OFFSET + radius, OFFSET + radius, radius, mPaint);//画左小圆
        canvas.drawCircle(OFFSET + WIDTH - radius, OFFSET + radius, radius, mPaint);//画右小圆

        mPaint.setXfermode(mXfermode);//加蒙层
        canvas.drawBitmap(mBitmap, OFFSET, OFFSET, mPaint);//画图片
        mPaint.setXfermode(null);//取消蒙层的绘制

        canvas.restore();

    }

    private Bitmap getAvater(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.cat, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.cat, options);
    }

}
