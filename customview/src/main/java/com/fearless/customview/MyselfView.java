package com.fearless.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.fearless.entity.ADEntity;

import java.util.List;

/**
 * Created by zhouwei on 16/9/30.
 */
public class MyselfView  extends View {

    private Paint mPaint;

    private  String text="京东大促销";

    private float mY=12;


    public List<ADEntity> getLs() {
        return ls;
    }

    public void setLs(List<ADEntity> ls) {
        this.ls = ls;
    }

    private List<ADEntity> ls;

    public MyselfView(Context context) {
        super(context);
        init();
    }

    public MyselfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyselfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    void init(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(90);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

           int height= getMeasuredHeight();

        Rect rect=new Rect();

       mPaint.getTextBounds(text,0,text.length(),rect);

        int textHeight=rect.bottom-rect.top;
        if(mY>height+textHeight){
            mY=0;
        }
        mY+=10;
       // Log.e("下标值下标值下标值","mY:"+mY);
            canvas.drawText(text,0,mY,mPaint);

           postInvalidateDelayed(20);
        }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
