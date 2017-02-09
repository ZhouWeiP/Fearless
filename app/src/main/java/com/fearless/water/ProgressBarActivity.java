package com.fearless.water;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by zhouwei on 16/10/24.
 */

public class ProgressBarActivity  extends AppCompatActivity {

    private ProgressBar pb_show;
    private TextView tv_progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar_activity);
        pb_show=(ProgressBar) findViewById(R.id.pb_show);
       // pb_show.setProgress(20);
        tv_progress=(TextView) findViewById(R.id.tv_progress);

        new Thread(){
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    try {
                         Thread.sleep(1000);
                        pb_show.setProgress(i);
                        tv_progress.setText("正在更新数据 ("+i+"%)");
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.e("",e.getMessage());
                    }

                    pb_show.setProgress(i);
                    
                }
            }
        }.start();




    }
}
