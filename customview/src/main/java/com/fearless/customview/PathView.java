package com.fearless.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouwei on 17/1/22.
 */

public class PathView  extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;



    public PathView(Context context) {
        super(context);
        initPaint();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心

//        Path path = new Path();
//        Path circle=new Path();
//        circle.addCircle(0,0,100, Path.Direction.CW);
//
//        path.addRect(-200,-200,200,200, Path.Direction.CCW);
//        path.addPath(circle,0,-200);
//                     //<-- 重置最后一个点的位置

        canvas.scale(1,-1);
        Path path=new Path();
        path.lineTo(100,100);

        RectF rectF=new RectF(0,0,300,300);
        path.arcTo(rectF,0,270);
        canvas.drawPath(path,mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth=w;
        mHeight=h;
    }
}
