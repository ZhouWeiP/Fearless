package com.fearless.water;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.fearless.sciencekeyboard.ScienceKeyBoard;


/**
 * Created by zhouwei on 17/1/11.
 */

public class KeyboardActivity extends Activity {

    EditText edt1;
    EditText edt2;
    EditText edt3;
    EditText edt4;
    EditText edt5;
    EditText edt6;
    EditText edt7;
    EditText edt8;
    EditText edt9;
    EditText edt10;
    EditText edt11;
    EditText edt12;

    PopupKeyboardUtil smallKeyboardUtil;
    private View viewContainer;

    ScienceKeyBoard mScienceKeyBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=LayoutInflater.from(this).inflate(R.layout.activity_keyboard,null);
        setContentView(view);



        edt1=(EditText) view.findViewById(R.id.edt1);
        edt2=(EditText) view.findViewById(R.id.edt2);
        edt3=(EditText) view.findViewById(R.id.edt3);
        edt4=(EditText) view.findViewById(R.id.edt4);
        edt5=(EditText) view.findViewById(R.id.edt5);
        edt6=(EditText) view.findViewById(R.id.edt6);
        edt7=(EditText) view.findViewById(R.id.edt7);
        edt8=(EditText) view.findViewById(R.id.edt8);
        edt9=(EditText) view.findViewById(R.id.edt9);
        edt10=(EditText) view.findViewById(R.id.edt10);
        edt11=(EditText) view.findViewById(R.id.edt11);
        edt12=(EditText) view.findViewById(R.id.edt12);




        ScienceKeyBoard scienceKeyBoard=new ScienceKeyBoard(this);
        scienceKeyBoard.attachTo(edt1, true);
        scienceKeyBoard.setAutoShowOnFocs(true);
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt1, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt2, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt3, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt4, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt5, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt6, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt7, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt8, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt9, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt10, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt11, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//
//
//        smallKeyboardUtil = new PopupKeyboardUtil(self());
//        smallKeyboardUtil.attachTo(edt12, true);
//        smallKeyboardUtil.setAutoShowOnFocs(true);
//



//        mScienceKeyBoard=new ScienceKeyBoard(this);
//        view.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               mScienceKeyBoard.show(getWindow().getDecorView());
//           }
//       });
    }





//    public void onClickView(View view) {
//        if (view.getId() == R.id.btn1)
//            smallKeyboardUtil.showSoftKeyboard();
//        if (view.getId() == R.id.btn2)
//            smallKeyboardUtil.hideSoftKeyboard();
//
//    }

    private Activity self() {
        return this;
    }
}
