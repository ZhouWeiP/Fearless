package com.fearless.water;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by zhouwei on 16/10/20.
 */

public class RxJavaActivity extends AppCompatActivity {

    private TextView tv_name;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.e("RxJavaActivityonCreate","RxJavaActivityonCreate");
        setContentView(R.layout.activity_main);
        tv_name=(TextView) findViewById(R.id.tv_name);


    }
}
