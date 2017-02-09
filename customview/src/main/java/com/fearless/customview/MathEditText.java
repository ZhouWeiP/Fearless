package com.fearless.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by zhouwei on 17/2/6.
 */

public class MathEditText extends View {


    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private LinkedList<TextModle> content=new LinkedList<TextModle>();





   void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);

    }

    public MathEditText(Context context) {
        super(context);
    }

    public MathEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



    }


    void analyzeText(Canvas canvas){
        for(int i=0;i<content.size();i++){
            TextModle item=content.get(i);
            switch (item.getText()){
                case "#sqart":

                    break;

            }

        }
    }






    void addText(String text){
        TextModle textModle=TextModle.newInstance(text);
        content.add(textModle);
        invalidate();
    }

    void setText(String text){

        invalidate();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;

    }
}
