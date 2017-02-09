package com.fearless.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouwei on 17/1/7.
 */

public class ScaleCanvasTestView extends View {
    public ScaleCanvasTestView(Context context) {
        super(context);
        initPaint();
    }

    public ScaleCanvasTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    Paint mPaint;

    void initPaint(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2,mHeight/2);  //将坐标系原点移动画布中心

       // Rect rect=new Rect(0,-400,400,0);
       //canvas.drawRect(rect,mPaint);
//
//
//
//        canvas.scale(-0.5f,-0.5f,200,0);
//
//        mPaint.setColor(Color.BLACK);
//
//        canvas.drawRect(rect,mPaint);

//
//           for (int i=0;i<20;i++){
//               canvas.scale(0.95f,0.95f);
//               canvas.drawRect(rect,mPaint);
//           }

//        canvas.rotate(180,200,0);
//
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(rect,mPaint);

//        canvas.drawCircle(0,0,400,mPaint);
//        canvas.drawCircle(0,0,380,mPaint);
//
//        for (int i=0;i<12;i++){
//            canvas.drawLine(0,380,0,400,mPaint);
//            canvas.rotate(30);
//
//        }


        Rect rect=new Rect(0,0,200,200);
        canvas.drawRect(rect,mPaint);

        canvas.skew(1,0);

        canvas.skew(0,1);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect,mPaint);

    }


    private int mWidth;
    private int mHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }
}
