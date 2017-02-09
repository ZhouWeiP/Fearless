package com.fearless.water;

import android.app.Activity;
import android.os.Bundle;

import com.fearless.customview.PieView;
import com.fearless.entity.PieData;

import java.util.ArrayList;

/**
 * Created by zhouwei on 16/12/22.
 */

public class PieViewActivity extends Activity {

    private PieView mPieView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieview);
        mPieView=(PieView) findViewById(R.id.custom_pieview);

        ArrayList<PieData> arrayList=new ArrayList<PieData>();

        PieData pieData1=new PieData();
        pieData1.setValue(30);
        PieData pieData2=new PieData();
        pieData2.setValue(150);
        PieData pieData3=new PieData();
        pieData3.setValue(80);
        arrayList.add(pieData1);
        arrayList.add(pieData2);
        arrayList.add(pieData3);
        mPieView.setData(arrayList);
    }
}
