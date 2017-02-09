package com.fearless.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.fearless.entity.ADEntity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.fearless.util.DisplayUtil;

/**
 * Created by zhouwei on 16/9/30.
 */
public class ADTextView  extends View {

    private int mSpeed; // 文字出现或消息的速度，建议1-5
    private int mInterval; //文字停留在中间的时长
    private int mFrontColor; //前缀颜色
    private int mContentColor; //内容颜色
    private int mFrontTextSize; //前缀文字大小
    private int mContentTextSize; //内容文字大小

    public List<ADEntity> getEntityList() {
        return mEntityList;
    }

    public void setEntityList(List<ADEntity> entityList) {
        mEntityList = entityList;
    }

    private List<ADEntity> mEntityList; //显示的文字集合
    private int mY; //文字的Y坐标
    private int mIndex; //当前的数据下标
    private Paint mFrontPaint; //绘制前缀的画笔
    private Paint mContentPaint; //绘制内容的画笔

    private boolean isMove=true; //是否移动


    private String TAG="ADTextView";

    private boolean  hasInit;
    private boolean  isPaused;





    public ADTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        obtainStyledAttrs(attrs);
        init();
    }


    public  void obtainStyledAttrs(AttributeSet attrs){
        TypedArray typedArray=getContext().obtainStyledAttributes(attrs,R.styleable.ADTextView);
        mSpeed=typedArray.getInteger(R.styleable.ADTextView_ad_text_view_speed,1);
        mInterval=typedArray.getInteger(R.styleable.ADTextView_ad_text_view_internal,2000);
        mFrontColor=typedArray.getColor(R.styleable.ADTextView_ad_text_front_color, Color.RED);
        mFrontTextSize=(int)typedArray.getDimension(R.styleable.ADTextView_ad_text_front_size, DisplayUtil.sp2pxFloat(getContext(),15));

        mContentColor=typedArray.getColor(R.styleable.ADTextView_ad_text_content_color,Color.BLACK);
        mContentTextSize=(int)typedArray.getDimension(R.styleable.ADTextView_ad_text_content_size,DisplayUtil.sp2pxFloat(getContext(),15));
        typedArray.recycle();

    }

    //初始化默认值
    public void init(){
       mIndex=0;
        mFrontPaint=new Paint();
        mFrontPaint.setDither(true);
        mFrontPaint.setAntiAlias(true);
        mFrontPaint.setTextSize(mFrontTextSize);
        mFrontPaint.setColor(mFrontColor);

        mContentPaint=new Paint();
        mContentPaint.setDither(true);
        mContentPaint.setAntiAlias(true);
        mContentPaint.setColor(mContentColor);
        mContentPaint.setTextSize(mContentTextSize);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=measureWidth(widthMeasureSpec);
        int height=measureHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

   private int  measureHeight(int heightMeasureSpec){
       int result=0;
       int mode=MeasureSpec.getMode(heightMeasureSpec);
       int size=MeasureSpec.getSize(heightMeasureSpec);

       if(mode==MeasureSpec.EXACTLY){
           result=size;
       }else{
           int mFrontTextHeight=(int)(mFrontPaint.descent()-mFrontPaint.ascent());
           int mContentTextHeight=(int)(mContentPaint.descent()-mFrontPaint.ascent());
           result=Math.max(mFrontTextHeight,mContentTextHeight);

         if(mode==MeasureSpec.AT_MOST){
             result=Math.min(result,size);
         }

       }
      return  result;
   }


    private int measureWidth(int widthMeasureSpec){
        int result=0;
        int mode=MeasureSpec.getMode(widthMeasureSpec);
        int size=MeasureSpec.getSize(widthMeasureSpec);

        if(mode==MeasureSpec.EXACTLY){
            result=size;
        }else{
            String text="十个字的大小十个字字";
            Rect rect=new Rect();
            mContentPaint.getTextBounds(text,0,text.length(),rect);
            result=rect.right-rect.left;

            if(mode==MeasureSpec.AT_MOST){
                result=Math.min(result,size);
            }

        }
        return  result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mEntityList != null) {
            ADEntity model = mEntityList.get(mIndex);
            String font = model.getFront();
            String back = model.getBack();
            //绘制前缀
            Rect indexBound = new Rect();
            mFrontPaint.getTextBounds(font, 0, font.length(), indexBound);

            //绘制内容文字
            Rect contentBound = new Rect();
            mContentPaint.getTextBounds(back, 0, back.length(), contentBound);
            if (mY == 0 && hasInit == false) {
                mY = getMeasuredHeight() - indexBound.top;
                hasInit = true;
            }
            //移动到最上面
            if (mY <= 0 - indexBound.bottom) {
               // KLog.i(TAG, "onDraw: " + getMeasuredHeight());
                mY = getMeasuredHeight() - indexBound.top;
                mIndex++;
                isPaused = false;
            }
            canvas.drawText(back, 0, back.length(), (indexBound.right - indexBound.left) + 20, mY, mFrontPaint);
            canvas.drawText(font, 0, font.length(), 10, mY, mContentPaint);
            //移动到中间
            if (!isPaused && mY <= getMeasuredHeight() / 2 - (indexBound.top + indexBound.bottom) / 2) {
                isMove = false;
                isPaused = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        postInvalidate();
                        isMove = true;
                    }
                }, mInterval);
            }
            mY -= mSpeed;
            //循环使用数据
            if (mIndex == mEntityList.size()) {
                mIndex = 0;
            }
            //如果是处于移动状态时的,则延迟绘制
            //计算公式为一个比例,一个时间间隔移动组件高度,则多少毫秒来移动1像素
            if (isMove) {
                postInvalidateDelayed(2);
            }
        }

    }

}
