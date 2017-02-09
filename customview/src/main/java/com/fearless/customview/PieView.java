package com.fearless.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.fearless.entity.PieData;

import java.util.ArrayList;

/**
 * Created by zhouwei on 16/12/22.
 */

public class PieView extends View {

    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};


    public ArrayList<PieData> getData() {
        return mData;
    }

    public void setData(ArrayList<PieData> data) {
        mData = data;
        initData();
        invalidate();
    }

    // 数据
    private ArrayList<PieData> mData;

    // 宽高
    private int mWidth, mHeight;

    // 画笔
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }



    private int mStartAngle;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mData==null||mData.size()==0){
            Toast.makeText(getContext(),"集合数据为空不能刷新",Toast.LENGTH_SHORT).show();
            return ;
        }


        mPaint.setColor(0xFF1586ED);



        int center=Math.min(mWidth,mHeight)/2;


        canvas.drawRect(0,0,center*2,center*2,mPaint);

        int radius=(int)(center*0.8);

        canvas.translate(center,center);
        RectF rectF=new RectF(-radius,-radius,radius,radius);

        int currentAngle=mStartAngle;
        for (int i=0;i<mData.size();i++){
            PieData pieData=mData.get(i);
            mPaint.setColor(pieData.getColor());
            int angle=(int)pieData.getAngle();
            canvas.drawArc(rectF,currentAngle,angle,true,mPaint);
            currentAngle+=angle;
        }



    }




    void initData(){

        if(mData==null||mData.size()==0){
            Toast.makeText(getContext(),"集合数据为空不能刷新",Toast.LENGTH_SHORT).show();
            return ;
        }


        int sumValue=0;
        for (int i=0;i<mData.size();i++){
            PieData pieData=mData.get(i);
            sumValue+=pieData.getValue();

        }


        for (int i=0;i<mData.size();i++){
            PieData pieData=mData.get(i);
            float value=pieData.getValue();
            float percent=(value/sumValue);
            int angle=(int)(percent*360);
            pieData.setAngle(angle);


            pieData.setColor(mColors[i%mColors.length]);
        }
    }
}
