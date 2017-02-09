package com.fearless.water;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhouwei on 17/1/22.
 */

public class PolygonView extends View {

    private int count=6; //多边形数量
    private float angle=(float)(Math.PI*2/count);
    private float radius;  //半径
    private int centerX;   //中心X
    private int centerY;   //中心Y
    private String[] titles=new String[]{"a","b","c","d","e","f"};
    private int[] data=new int[]{100,50,30,100,70,66};
    private float maxValue=100;

    private int mWidth;
    private int mHeight;

    private Paint mainPaint;   //雷达区画笔

    private Paint valuePaint;   //数据区画笔

    private Paint textPaint;    //文本区画笔




    public PolygonView(Context context) {
        super(context);
        initPaint();
    }

    public PolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();

    }

    void initPaint(){
        mainPaint=new Paint();
        valuePaint=new Paint();
        textPaint=new Paint();


        mainPaint.setColor(Color.BLACK);
        valuePaint.setColor(Color.BLUE);
        textPaint.setColor(Color.RED);

        mainPaint.setStyle(Paint.Style.STROKE);


        textPaint.setTextSize(30);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas){
        Path path = new Path();
        float r = radius/(count-1);//r是蜘蛛丝之间的间距
        for(int i=1;i<count;i++){//中心点不用绘制
            float curR = r*i;//当前半径
            path.reset();
            for(int j=0;j<count;j++){
                if(j==0){
                    path.moveTo(centerX+curR,centerY);
                    Log.e("多边形起始坐标点:","x:"+(centerX+curR)+"y:"+centerY);
                }else{
                    //根据半径，计算出蜘蛛丝上每个点的坐标

                    float x = (float) (centerX+curR*Math.cos(angle*j));
                    float y = (float) (centerY+curR*Math.sin(angle*j));
                    path.lineTo(x,y);
                    Log.e("多边形cos值:",Math.cos(angle*j)+"");
                    Log.e("多边形连线角度:","r:"+r+" j:"+j+" angle:"+(angle*j)+"Math.cos(angle*j):"+Math.cos(angle*j));
                    Log.e("多边形连线坐标点:","x:"+x+" y:"+y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mainPaint);
        }
    }


    /**
     * 绘制直线
     */
    private void drawLines(Canvas canvas){
        Path path = new Path();
        for(int i=0;i<count;i++){
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX+radius*Math.cos(angle*i));
            float y = (float) (centerY+radius*Math.sin(angle*i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }



    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas){
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for(int i=0;i<count;i++){
            float x = (float) (centerX+(radius+fontHeight/2)*Math.cos(angle*i));
            float y = (float) (centerY+(radius+fontHeight/2)*Math.sin(angle*i));
            if(angle*i>=0&&angle*i<=Math.PI/2){//第4象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>=3*Math.PI/2&&angle*i<=Math.PI*2){//第3象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>Math.PI/2&&angle*i<=Math.PI){//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }else if(angle*i>=Math.PI&&angle*i<3*Math.PI/2){//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }
        }
    }


    /**
     * 绘制区域
     * @param canvas
     */
    private void drawRegion(Canvas canvas){
        Path path = new Path();
        valuePaint.setAlpha(255);
        for(int i=0;i<count;i++){
            double percent = data[i]/maxValue;
            float x = (float) (centerX+radius*Math.cos(angle*i)*percent);
            float y = (float) (centerY+radius*Math.sin(angle*i)*percent);
            if(i==0){
                path.moveTo(x, centerY);
            }else{
                path.lineTo(x,y);
            }
            //绘制小圆点
            canvas.drawCircle(x,y,10,valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius=Math.min(w,h)/2*0.9f;

        centerX=w/2;
        centerY=h/2;
         postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
