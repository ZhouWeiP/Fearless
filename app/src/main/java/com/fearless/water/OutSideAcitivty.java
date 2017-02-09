package com.fearless.water;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zhouwei on 16/11/16.
 */

public class OutSideAcitivty  extends AppCompatActivity {

    LinearLayout ll_block;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_outside);
        ll_block=(LinearLayout)findViewById(R.id.ll_block);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int left=ll_block.getLeft();
        int top=ll_block.getTop();
        int right=ll_block.getRight();
        int botm=ll_block.getBottom();
        if(event.getX()>=left)
        return super.onTouchEvent(event);
        return  true;

    }
}
