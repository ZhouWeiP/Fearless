package com.fearless.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.view.View;

import cn.fearless.util.DisplayUtil;

/**
 * Created by zhouwei on 16/9/13.
 */
public class WatchBoard extends View {


    private float mRadius; //外圆半径
    private float mPadding; //边距
    private float mTextSize; //文字大小
    private float mHourPointWidth; //时针宽度
    private float mMinutePointWidth; //分针宽度
    private float mSecondPointWidth; //秒针宽度
    private int mPointRadius; // 指针圆角
    private float mPointEndLength; //指针末尾的长度

    private int mColorLong; //长线的颜色
    private int mColorShort; //短线的颜色
    private int mHourPointColor; //时针的颜色
    private int mMinutePointColor; //分针的颜色
    private int mSecondPointColor; //秒针的颜色

    private Paint mPaint; //画笔







    public float getmRadius() {
        return mRadius;
    }

    public void setmRadius(float mRadius) {
        this.mRadius = mRadius;
    }

    public float getmPadding() {
        return mPadding;
    }

    public void setmPadding(float mPadding) {
        this.mPadding = mPadding;
    }

    public float getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    public float getmHourPointWidth() {
        return mHourPointWidth;
    }

    public void setmHourPointWidth(float mHourPointWidth) {
        this.mHourPointWidth = mHourPointWidth;
    }

    public float getmMinutePointWidth() {
        return mMinutePointWidth;
    }

    public void setmMinutePointWidth(float mMinutePointWidth) {
        this.mMinutePointWidth = mMinutePointWidth;
    }

    public float getmSecondPointWidth() {
        return mSecondPointWidth;
    }

    public void setmSecondPointWidth(float mSecondPointWidth) {
        this.mSecondPointWidth = mSecondPointWidth;
    }

    public int getmPointRadius() {
        return mPointRadius;
    }

    public void setmPointRadius(int mPointRadius) {
        this.mPointRadius = mPointRadius;
    }

    public float getmPointEndLength() {
        return mPointEndLength;
    }

    public void setmPointEndLength(float mPointEndLength) {
        this.mPointEndLength = mPointEndLength;
    }

    public int getmColorLong() {
        return mColorLong;
    }

    public void setmColorLong(int mColorLong) {
        this.mColorLong = mColorLong;
    }

    public int getmColorShort() {
        return mColorShort;
    }

    public void setmColorShort(int mColorShort) {
        this.mColorShort = mColorShort;
    }

    public int getmHourPointColor() {
        return mHourPointColor;
    }

    public void setmHourPointColor(int mHourPointColor) {
        this.mHourPointColor = mHourPointColor;
    }

    public int getmMinutePointColor() {
        return mMinutePointColor;
    }

    public void setmMinutePointColor(int mMinutePointColor) {
        this.mMinutePointColor = mMinutePointColor;
    }

    public int getmSecondPointColor() {
        return mSecondPointColor;
    }

    public void setmSecondPointColor(int mSecondPointColor) {
        this.mSecondPointColor = mSecondPointColor;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    public WatchBoard(Context context) {
        super(context);
    }

    public WatchBoard(Context context, AttributeSet attrs) {

        super(context, attrs);
        obtainStyleAttrs(attrs);
        init();

    }


    //获取初始属性
    public void obtainStyleAttrs(AttributeSet attrs){
        TypedArray array=null;
        try{
            array=getContext().obtainStyledAttributes(attrs,R.styleable.WatchBoard);
            mPadding=array.getDimension(R.styleable.WatchBoard_wb_padding,DpToPx(10));
            mTextSize=array.getDimension(R.styleable.WatchBoard_wb_text_size,SpToPx(16)); //文字大小
            mHourPointWidth=array.getDimension(R.styleable.WatchBoard_wb_hour_pointer_width,DpToPx(5)); //时针宽度
            mMinutePointWidth=array.getDimension(R.styleable.WatchBoard_wb_minute_pointer_width,DpToPx(3)); //分针宽度
            mSecondPointWidth=array.getDimension(R.styleable.WatchBoard_wb_second_pointer_width,DpToPx(2)); //秒针宽度
            mPointRadius=(int)array.getDimension(R.styleable.WatchBoard_wb_pointer_corner_radius,DpToPx(10)); // 指针圆角
            mPointEndLength=array.getDimension(R.styleable.WatchBoard_wb_pointer_end_length,DpToPx(10)); //指针末尾的长度

            mColorLong=array.getColor(R.styleable.WatchBoard_wb_scale_long_color, Color.argb(225,0,0,0)); //长线的颜色
            mColorShort=array.getColor(R.styleable.WatchBoard_wb_scale_short_color,Color.argb(125,0,0,0)); //短线的颜色
            mHourPointColor=array.getColor(R.styleable.WatchBoard_wb_hour_pointer_color,Color.BLACK); //时针的颜色
            mMinutePointColor=array.getColor(R.styleable.WatchBoard_wb_minute_pointer_color,Color.BLACK);
            mSecondPointColor=array.getColor(R.styleable.WatchBoard_wb_second_pointer_color,Color.RED);


        }catch(Exception exception){
            mPadding=DpToPx(10);
            mTextSize=SpToPx(16); //文字大小
            mHourPointWidth=DpToPx(5); //时针宽度
            mMinutePointWidth=DpToPx(3); //分针宽度
            mSecondPointWidth=DpToPx(2); //秒针宽度
            mPointRadius=DpToPx(10); // 指针圆角
            mPointEndLength=DpToPx(10); //指针末尾的长度

            mColorLong=Color.argb(225,0,0,0); //长线的颜色
            mColorShort=Color.argb(125,0,0,0); //短线的颜色
            mHourPointColor=Color.BLACK; //时针的颜色
            mMinutePointColor=Color.BLACK;
            mSecondPointColor=Color.RED;

        }finally {
            if(array!=null){
                array.recycle();
            }
        }


    }


    public int DpToPx(float dp){
        return DisplayUtil.dip2px(getContext(),dp);
    }

    public int SpToPx(float sp){
        return DisplayUtil.dip2px(getContext(),sp);
    }

    //初始化画笔
    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }


    public static class NoDetermineSizeException extends Exception{

         public NoDetermineSizeException(String message){
              super(message);
         }

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=1000; //设定一个最小值
        int widthSize= MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heighMode=MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode==MeasureSpec.AT_MOST||widthMode==MeasureSpec.UNSPECIFIED||heighMode==MeasureSpec.AT_MOST||heighMode==MeasureSpec.UNSPECIFIED){
            try{
                throw new NoDetermineSizeException("高度或宽度至少有一个为确定值，不能同时为wrap_content");
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }else{
            //至少有一个为确定值，取最小值
            if(widthMode==MeasureSpec.AT_MOST){
                width=Math.min(widthSize,width);
            }
            if(heighMode==MeasureSpec.AT_MOST){
                width=Math.min(heightSize,width);
            }

        }

        setMeasuredDimension(width,width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadius=(Math.min(w,h)-mPadding)/2;
        mPointEndLength=mRadius/6;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(getWidth()/2,getHeight()/2);
        paintCircle(canvas);
        paintScale(canvas);
        paintPointer(canvas);
        canvas.restore();
        postInvalidateDelayed(1000);
    }

    @TargetApi(24)
    private void paintPointer(Canvas canvas) {

        Calendar calendar= null;

            calendar =Calendar.getInstance();


        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);


        int angleHour=(hour%12)*360/12;
        int angleMinute=minute*360/60;
        int angleSecond = second * 360 / 60; //秒针转过的角度

        //绘制时针
        canvas.save();
        canvas.rotate(angleHour); //旋转到时针的角度
        RectF rectFHour = new RectF(-mHourPointWidth / 2, -mRadius * 3 / 5, mHourPointWidth / 2, mPointEndLength);
        mPaint.setColor(mHourPointColor); //设置指针颜色
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mHourPointWidth); //设置边界宽度
        canvas.drawRoundRect(rectFHour, mPointRadius, mPointRadius, mPaint); //绘制时针
        canvas.restore();
        //绘制分针
        canvas.save();
        canvas.rotate(angleMinute);
        RectF rectFMinute = new RectF(-mMinutePointWidth / 2, -mRadius * 3.5f / 5, mMinutePointWidth / 2, mPointEndLength);
        mPaint.setColor(mMinutePointColor);
        mPaint.setStrokeWidth(mMinutePointWidth);
        canvas.drawRoundRect(rectFMinute, mPointRadius, mPointRadius, mPaint);
        canvas.restore();
        //绘制秒针
        canvas.save();
        canvas.rotate(angleSecond);
        RectF rectFSecond = new RectF(-mSecondPointWidth / 2, -mRadius + 15, mSecondPointWidth / 2, mPointEndLength);
        mPaint.setColor(mSecondPointColor);
        mPaint.setStrokeWidth(mSecondPointWidth);
        canvas.drawRoundRect(rectFSecond, mPointRadius, mPointRadius, mPaint);
        canvas.restore();
        //绘制中心小圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mSecondPointColor);
        canvas.drawCircle(0, 0, mSecondPointWidth * 4, mPaint);

    }

    private void paintScale(Canvas canvas) {
        int lineWidth;
        for(int i=0;i<60;i++){

            if(i%5==0){
                mPaint.setColor(mColorLong);
                mPaint.setStrokeWidth(DisplayUtil.dip2px(getContext(),1.5f));
                lineWidth=40;

                mPaint.setTextSize(mTextSize);
                String text=i==0?12+"":i/5+"";
                Rect textBound=new Rect();
                mPaint.getTextBounds(text,0,text.length(),textBound);
                mPaint.setColor(Color.BLACK);
                canvas.save();
                canvas.translate(0,-mRadius+DpToPx(10)+lineWidth+(textBound.bottom-textBound.top));
                canvas.rotate(-6*i);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawText(text,-(textBound.right-textBound.left)/2,textBound.bottom,mPaint);
                canvas.restore();
            }else{
                mPaint.setColor(mColorShort);
                mPaint.setStrokeWidth(DisplayUtil.dip2px(getContext(),1f));
                lineWidth=30;
            }
            canvas.drawLine(0,-mRadius+DpToPx(10),0,0-mRadius+DpToPx(10)+lineWidth,mPaint);
            canvas.rotate(6);
        }
    }

    private void paintCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0,0,mRadius,mPaint);
    }
}
