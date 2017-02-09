package com.fearless.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouwei on 16/12/10.
 */

public class CanvasTestView  extends View {


    public CanvasTestView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    Paint paint;
    void initPaint(){
        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.black));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(12);

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(getResources().getColor(android.R.color.holo_green_light));

        float[] points=new float[]{120,140,270,564,77,743,780,543};

        canvas.drawPoints(points,paint);

        paint.setColor(getResources().getColor(R.color.color_668584));
        canvas.drawLine(200,300,600,450,paint);

        float[] lines=new float[]{132,145,568,437,753,235,793,574};
        canvas.drawLines(lines,paint);

//        canvas.drawRect(100,200,700,600,paint);

//        Rect rect=new Rect(200,300,800,700);
//        canvas.drawRect(rect,paint);
//
//
//        RectF rectF=new RectF(500,600,800,1000);
//        canvas.drawRect(rectF,paint);


        RectF rectF1=new RectF(50,100,850,400);
        canvas.drawRect(rectF1,paint);
        paint.setColor(getResources().getColor(R.color.color_235424));
        canvas.drawRoundRect(rectF1,300,100,paint);


        RectF rectF=new RectF(500,500, 900,900);
        canvas.drawRect(rectF,paint);
        paint.setColor(getResources().getColor(R.color.black));
        canvas.drawArc(rectF,0,90,false,paint);




    }
}
