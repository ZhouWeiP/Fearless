package com.fearless.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhouwei on 16/10/9.
 */
public class RoundProgressBar extends View {

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public synchronized Integer getMax() {
        return max;
    }

    public synchronized void setMax(Integer max) {
        if(max<0){
            throw  new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    public boolean isTextIsDisplayable() {
        return textIsDisplayable;
    }

    public void setTextIsDisplayable(boolean textIsDisplayable) {
        this.textIsDisplayable = textIsDisplayable;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    //圆环的颜色
    private int roundColor;
    //圆环进度的颜色
    private int roundProgressColor;
    //圆环的宽度
    private float roundWidth;
    //文字颜色
    private int textColor;
    //文字大小
    private float textSize;
    //最大进度
    private Integer max;
    //文字是否可显示
    private boolean textIsDisplayable;
    //进度的风格,是空心还是实心
    private int style;
    //状态图片id
    private int statusImageResourceId;

    public int getStatusImageResourceId() {
        return statusImageResourceId;
    }

    public void setStatusImageResourceId(int statusImageResourceId) {
        this.statusImageResourceId = statusImageResourceId;
    }




    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private  String text;

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if(progress<0){
            throw  new IllegalArgumentException("max not less than 0");
        }

        if(progress>max){
            progress=max;
        }

        if(progress<=max){
            this.progress=progress;
            postInvalidate();
        }
    }

    //当前进度
    private int progress;
    private int status; //0安排了作业单但没有做,不显示状态 1没有安排作业显示空 2显示锁住

    private static final String TAG="RoundProgressBar";

    private static final int STROKE=0;
    private static final int FILL=1;
    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    private Paint paint;

    public RoundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttrs(attrs);
    }

    public RoundProgressBar(Context context) {
        this(context,null);

    }

    public void obtainStyledAttrs(AttributeSet attrs){
        TypedArray typedArray=getContext().obtainStyledAttributes(attrs,R.styleable.RoundProgressBar);
        paint=new Paint();
        roundColor=typedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.argb(255,163,163,163));
        roundProgressColor=typedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor,Color.RED);
        roundWidth=typedArray.getDimension(R.styleable.RoundProgressBar_roundWidth,5);
        textColor=typedArray.getColor(R.styleable.RoundProgressBar_textColor,Color.BLACK);
        textSize=typedArray.getDimension(R.styleable.RoundProgressBar_textSize,60f);
        max=typedArray.getInteger(R.styleable.RoundProgressBar_max,100);
        textIsDisplayable=typedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable,true);
        style=typedArray.getInt(R.styleable.RoundProgressBar_style,0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画最外层的大圆环
        int center=Math.min(getWidth(),getHeight())/2;   //获取圆心的x坐标
        int radius=(int) (center - roundWidth/2); //圆环的半径
        paint.setColor(roundColor); //设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(roundWidth);  //设置圆环的宽度
        paint.setAntiAlias(true); //消除锯齿
        canvas.drawCircle(center,center,radius,paint);
        Log.e(TAG,"总宽度:"+getWidth()+"总高度:"+getHeight()+"圆心的坐标:"+center+"半径:"+radius);

        drawBitmap(canvas,center);
        drawText(canvas, center);





        //画圆弧(圆环的进度)
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundProgressColor);
        //float left, float top, float right, float bottom
        RectF oval = new RectF(center - radius, center - radius, center
                + radius, center + radius);  //用于定义的圆弧的形状和大小的界限
        switch (style) {
            case STROKE:
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, 90, 360 * progress / max, false, paint);  //根据进度画圆弧
                break;

            case FILL:
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0)
                    canvas.drawArc(oval, 0, 360 * progress / max, true, paint);  //根据进度画圆弧
                break;
      }

    }

    private void drawCircleRound() {
    }

    private void drawText(Canvas canvas, int center) {
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体
        // int percent = (int)(((float)progress / (float)max) * 100);  //中间的进度百分比，先转换成float在进行除法运算，不然都为0
        float textWidth = paint.measureText(text);   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        Log.e(TAG,"文本:"+text);
       // canvas.drawText(text,0, center + textSize/2, paint); //画出进度百分比
        canvas.drawText(text, center - textWidth / 2, center + textSize/2, paint);
        //
    }

    private void drawBitmap(Canvas canvas,int radius) {

        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),getStatusImageResourceId());
        if(bitmap==null){
            return;
        }
        int viewWidth=getWidth();
        int viewHeight=getHeight();

        int bitmapWidth=bitmap.getWidth();
        int bitmapHeight=bitmap.getHeight();

        int centerLeft=(viewWidth-radius)/2;
        int centerTop=(viewHeight-radius)/2;

        Rect srcRect=new Rect(0,0,bitmapWidth,bitmapHeight);

        Rect destRect=new Rect(centerLeft,centerTop,(centerLeft+radius),(centerTop+radius));


        canvas.drawBitmap(bitmap,null,destRect,paint);
    }


}
