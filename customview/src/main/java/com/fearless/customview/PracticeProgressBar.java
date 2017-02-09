package com.fearless.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by zhouwei on 16/10/9.
 */

public class PracticeProgressBar extends RelativeLayout{

    private RoundProgressBar mRoundProgressBar;

    public PracticeProgressBar(Context context) {
        super(context);
    }

    public PracticeProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){
        mRoundProgressBar=new RoundProgressBar(getContext());
        //mRoundProgressBar.set
    }


}
