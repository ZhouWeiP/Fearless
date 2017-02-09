package com.fearless.sciencekeyboard;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fearless.water.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import static com.fearless.water.PopupKeyboardUtil.hideSystemSofeKeyboard;

/**
 * Created by zhouwei on 17/1/18.
 */

public class ScienceKeyBoard implements KeyBoardOnItemClickView, View.OnClickListener {

    Handler handler = new Handler();
    PopupWindow mPopupWindow;
    AutoLinearLayout ll_KeyBoardGroup;
    RelativeLayout rl_arrows_left;
    RelativeLayout rl_arrows_right;
    RelativeLayout rl_delete;
    RelativeLayout rl_functionlinefree;
    RelativeLayout rl_switchKeyBoard;
    RelativeLayout rl_chemistrykeyboard;
    RelativeLayout rl_physicskeyboard;
    RelativeLayout rl_systemkeyboard;


    ImageView iv_delete;
    ImageView iv_arrows_left;
    ImageView iv_arrows_right;
    TextView tv_functionlinefree;
    TextView tv_sciencekeyboard;
    ImageView iv_sciencekeyboard_arrows;

    TextView tv_chemistrykeyboard;
    TextView tv_physicskeyboard;
    TextView tv_systemkeykeyboard;

    AutoRelativeLayout rl_more;
    AutoRelativeLayout rl_more2;
    AutoLinearLayout auto_ll_bottom1;
    AutoLinearLayout auto_ll_bottom2;



    //底部键 第一页
    AutoRelativeLayout rl_key_pai;
    AutoRelativeLayout rl_key_jiajian;
    AutoRelativeLayout rl_key_budengyu;
    AutoRelativeLayout rl_key_ciyate;
    AutoRelativeLayout rl_key_shuxian;
    AutoRelativeLayout rl_key_baohan;
    AutoRelativeLayout rl_key_bingji;
    AutoRelativeLayout rl_key_jiaoji;

    //底部键 第一页
    AutoRelativeLayout rl_key_e;
    AutoRelativeLayout rl_key_jiajian2;
    AutoRelativeLayout rl_key_zuokuohao;
    AutoRelativeLayout rl_key_youkuohao;
    AutoRelativeLayout rl_key_zuozhongkuohao;
    AutoRelativeLayout rl_key_youzhongkuohao;
    AutoRelativeLayout rl_key_zuodakuohao;
    AutoRelativeLayout rl_key_youdakuohao;




    private RecyclerView rlv_keyboard;
    private KeyBoardKeyAdapter mKeyBoardKeyAdapter;
    private KeyBoardKeyMathPageSecondAdapter mKeyBoardKeyMathPageSecondAdapter;
    private static List<Integer> MATH_PAGE_FIRST = new ArrayList<Integer>();
    private static List<Integer> MATH_PAGE_SECOND = new ArrayList<Integer>();


    Activity mActivity;
    InputMethodManager imm;

    public ScienceKeyBoard(Activity mActivity) {

        this.mActivity = mActivity;
        imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        initScienceKeyBoard();

    }

    EditText mEditText;

    public void attachTo(EditText editText, boolean isAuto) {
        this.mEditText = editText;
        hideSystemSofeKeyboard(this.mEditText);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        setAutoShowOnFocs(isAuto);

    }


    public void setAutoShowOnFocs(boolean enable) {
        if (mEditText == null)
            return;
        if (enable)
            mEditText.setOnFocusChangeListener(onFocusChangeListener1);
        else
            mEditText.setOnFocusChangeListener(null);
    }


    public void showSoftKeyboard() {


        FrameLayout frameLayout = (FrameLayout) mActivity.getWindow().getDecorView();


        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        boolean isHave = navigationBarExist2(mActivity);
        if (isHave) {
            lp.bottomMargin = getNavigationBarHeight();
        } else {

        }

        frameLayout.addView(rootView, lp);


        //viewContainer.setVisibility(View.GONE);
        //  viewContainer.setAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.down_to_up));
    }


    public void hideSoftKeyboard() {
        if (rootView != null && rootView.getParent() != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }


    View.OnFocusChangeListener onFocusChangeListener1 = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                showSoftKeyboard();
            else
                hideSoftKeyboard();
        }
    };

    static {
        //小键盘第一页

        //第一页第一行
        MATH_PAGE_FIRST.add(R.mipmap.key_7);
        MATH_PAGE_FIRST.add(R.mipmap.key_8);
        MATH_PAGE_FIRST.add(R.mipmap.key_9);
        MATH_PAGE_FIRST.add(R.mipmap.key_jia);
        MATH_PAGE_FIRST.add(R.mipmap.key_douhao);
        MATH_PAGE_FIRST.add(R.mipmap.key_dayudengyu);
        MATH_PAGE_FIRST.add(R.mipmap.key_sqart1);


        //第一页第二行
        MATH_PAGE_FIRST.add(R.mipmap.key_4);
        MATH_PAGE_FIRST.add(R.mipmap.key_5);
        MATH_PAGE_FIRST.add(R.mipmap.key_6);
        MATH_PAGE_FIRST.add(R.mipmap.key_jian);
        MATH_PAGE_FIRST.add(R.mipmap.key_maohao);
        MATH_PAGE_FIRST.add(R.mipmap.key_xiaoyudengyu);
        MATH_PAGE_FIRST.add(R.mipmap.key_fenshu);


        //第一页第三行
        MATH_PAGE_FIRST.add(R.mipmap.key_1);
        MATH_PAGE_FIRST.add(R.mipmap.key_2);
        MATH_PAGE_FIRST.add(R.mipmap.key_3);
        MATH_PAGE_FIRST.add(R.mipmap.key_cheng);
        MATH_PAGE_FIRST.add(R.mipmap.key_dushu);
        MATH_PAGE_FIRST.add(R.mipmap.key_dayu);
        MATH_PAGE_FIRST.add(R.mipmap.key_pingfang);


        //第一页第四行
        MATH_PAGE_FIRST.add(R.mipmap.key_0);
        MATH_PAGE_FIRST.add(R.mipmap.key_dian);
        MATH_PAGE_FIRST.add(R.mipmap.key_dengyu);
        MATH_PAGE_FIRST.add(R.mipmap.key_chuyi);
        MATH_PAGE_FIRST.add(R.mipmap.key_baifenhao);
        MATH_PAGE_FIRST.add(R.mipmap.key_xiaoyu);
        MATH_PAGE_FIRST.add(R.mipmap.key_jueduizhi);

    }


    static {

        //键盘第二页

        //第一页
        MATH_PAGE_SECOND.add(R.mipmap.key_sin);
        MATH_PAGE_SECOND.add(R.mipmap.key_yue);
        MATH_PAGE_SECOND.add(R.mipmap.key_yuedengyu);

        MATH_PAGE_SECOND.add(R.mipmap.key_aerfa);
        MATH_PAGE_SECOND.add(R.mipmap.key_cigema);
        MATH_PAGE_SECOND.add(R.mipmap.key_dianxinyuan);


        //第二页
        MATH_PAGE_SECOND.add(R.mipmap.key_cos);
        MATH_PAGE_SECOND.add(R.mipmap.key_jiaodu);
        MATH_PAGE_SECOND.add(R.mipmap.key_tianping);
        MATH_PAGE_SECOND.add(R.mipmap.key_beita);
        MATH_PAGE_SECOND.add(R.mipmap.key_daosangedian);
        MATH_PAGE_SECOND.add(R.mipmap.key_qiaowan);


        //第三页
        MATH_PAGE_SECOND.add(R.mipmap.key_tan);
        MATH_PAGE_SECOND.add(R.mipmap.key_shuangxiegang);
        MATH_PAGE_SECOND.add(R.mipmap.key_tiandushu);
        MATH_PAGE_SECOND.add(R.mipmap.key_gama);
        MATH_PAGE_SECOND.add(R.mipmap.key_zhengsangedian);
        MATH_PAGE_SECOND.add(R.mipmap.key_xingxing);


        //第四页
        MATH_PAGE_SECOND.add(R.mipmap.key_cot);
        MATH_PAGE_SECOND.add(R.mipmap.key_zhixiangzuoyou);
        MATH_PAGE_SECOND.add(R.mipmap.key_zhixiangzuoyou);
        MATH_PAGE_SECOND.add(R.mipmap.key_lammota);
        MATH_PAGE_SECOND.add(R.mipmap.key_sanjiaoxing);
        MATH_PAGE_SECOND.add(R.mipmap.key_zhengfangxing);


    }


    View rootView;


    void initScienceKeyBoard() {
        rootView = LayoutInflater.from(mActivity).inflate(R.layout.view_sciencekeyboard_content, null);
        initView(rootView);
//        mPopupWindow.setContentView(rootView);
//        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());


    }


    public void show(View view) {
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    }


    private void initView(View view) {
        ll_KeyBoardGroup = (AutoLinearLayout) view.findViewById(R.id.ll_KeyBoardGroup);
        rl_arrows_left = (RelativeLayout) view.findViewById(R.id.rl_arrows_left);
        rl_arrows_right = (RelativeLayout) view.findViewById(R.id.rl_arrows_right);
        rl_delete = (RelativeLayout) view.findViewById(R.id.rl_delete);
        rl_functionlinefree = (RelativeLayout) view.findViewById(R.id.rl_functionlinefree);
        rl_switchKeyBoard = (RelativeLayout) view.findViewById(R.id.rl_switchKeyBoard);

        rl_chemistrykeyboard = (RelativeLayout) view.findViewById(R.id.rl_chemistrykeyboard);
        rl_physicskeyboard = (RelativeLayout) view.findViewById(R.id.rl_physicskeyboard);
        rl_systemkeyboard = (RelativeLayout) view.findViewById(R.id.rl_systemkeyboard);





        iv_arrows_left = (ImageView) view.findViewById(R.id.iv_arrows_left);
        iv_arrows_right = (ImageView) view.findViewById(R.id.iv_arrows_right);
        iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
        tv_functionlinefree = (TextView) view.findViewById(R.id.tv_functionlinefree);
        tv_sciencekeyboard = (TextView) view.findViewById(R.id.tv_sciencekeyboard);
        iv_sciencekeyboard_arrows = (ImageView) view.findViewById(R.id.iv_sciencekeyboard_arrows);

        tv_chemistrykeyboard = (TextView) view.findViewById(R.id.tv_chemistrykeyboard);
        tv_physicskeyboard = (TextView) view.findViewById(R.id.tv_physicskeyboard);
        tv_systemkeykeyboard = (TextView) view.findViewById(R.id.tv_systemkeykeyboard);


        rlv_keyboard = (RecyclerView) view.findViewById(R.id.rlv_keyboard);
        rl_more = (AutoRelativeLayout) view.findViewById(R.id.rl_more);
        rl_more2 = (AutoRelativeLayout) view.findViewById(R.id.rl_more2);
        auto_ll_bottom1 = (AutoLinearLayout) view.findViewById(R.id.auto_ll_bottom1);
        auto_ll_bottom2 = (AutoLinearLayout) view.findViewById(R.id.auto_ll_bottom2);



        rl_key_pai=(AutoRelativeLayout) view.findViewById(R.id.rl_key_pai);
        rl_key_jiajian=(AutoRelativeLayout) view.findViewById(R.id.rl_key_jiajian);
        rl_key_budengyu=(AutoRelativeLayout) view.findViewById(R.id.rl_key_budengyu);
        rl_key_ciyate=(AutoRelativeLayout) view.findViewById(R.id.rl_key_ciyate);
        rl_key_shuxian=(AutoRelativeLayout) view.findViewById(R.id.rl_key_shuxian);
        rl_key_baohan=(AutoRelativeLayout) view.findViewById(R.id.rl_key_baohan);
        rl_key_bingji=(AutoRelativeLayout) view.findViewById(R.id.rl_key_bingji);
        rl_key_jiaoji=(AutoRelativeLayout) view.findViewById(R.id.rl_key_jiaoji);


        rl_key_e=(AutoRelativeLayout) view.findViewById(R.id.rl_key_e);
        rl_key_jiajian2=(AutoRelativeLayout)view.findViewById(R.id.rl_key_jiajian2);
        rl_key_zuokuohao=(AutoRelativeLayout)view.findViewById(R.id.rl_key_zuokuohao);
        rl_key_youkuohao=(AutoRelativeLayout)view.findViewById(R.id.rl_key_youkuohao);
        rl_key_zuozhongkuohao=(AutoRelativeLayout)view.findViewById(R.id.rl_key_zuozhongkuohao);
        rl_key_youzhongkuohao=(AutoRelativeLayout)view.findViewById(R.id.rl_key_youzhongkuohao);
        rl_key_zuodakuohao=(AutoRelativeLayout)view.findViewById(R.id.rl_key_zuodakuohao);
        rl_key_youdakuohao=(AutoRelativeLayout)view.findViewById(R.id.rl_key_youdakuohao);



        rl_key_pai.setOnClickListener(this);
        rl_key_jiajian.setOnClickListener(this);
        rl_key_budengyu.setOnClickListener(this);
        rl_key_ciyate.setOnClickListener(this);
        rl_key_shuxian.setOnClickListener(this);
        rl_key_baohan.setOnClickListener(this);
        rl_key_bingji.setOnClickListener(this);
        rl_key_jiaoji.setOnClickListener(this);

        rl_key_e.setOnClickListener(this);
        rl_key_jiajian2.setOnClickListener(this);
        rl_key_zuokuohao.setOnClickListener(this);
        rl_key_youkuohao.setOnClickListener(this);
        rl_key_zuozhongkuohao.setOnClickListener(this);
        rl_key_youzhongkuohao.setOnClickListener(this);
        rl_key_zuodakuohao.setOnClickListener(this);
        rl_key_youdakuohao.setOnClickListener(this);


        rl_arrows_left.setOnClickListener(this);
        rl_arrows_right.setOnClickListener(this);

        rl_more.setOnClickListener(this);
        rl_more2.setOnClickListener(this);


        mKeyBoardKeyMathPageSecondAdapter = new KeyBoardKeyMathPageSecondAdapter(mActivity, this);
        mKeyBoardKeyAdapter = new KeyBoardKeyAdapter(mActivity, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 7);
        rlv_keyboard.setLayoutManager(gridLayoutManager);
        SpaceItemDecoration spacesItemDecoration = new SpaceItemDecoration(12);
        rlv_keyboard.addItemDecoration(spacesItemDecoration);

        rlv_keyboard.setAdapter(mKeyBoardKeyAdapter);
        mKeyBoardKeyAdapter.setData(MATH_PAGE_FIRST);

        initOptionTouchListener();

    }


    void initOptionTouchListener() {
        rl_arrows_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("点击效果ACTION_DOWN", "点击效果ACTION_DOWN");
                        int start = mEditText.getSelectionStart();
                        if(start==0){

                        }else{
                            Toast.makeText(mActivity, "下标:"+start+"",Toast.LENGTH_SHORT);
                            mEditText.setSelection(start-1);
                        }

                        rl_arrows_left.setBackgroundColor(Color.WHITE);
                        iv_arrows_left.setImageResource(R.mipmap.icon_left_black);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.e("点击效果ACTION_UP", "点击效果ACTION_DOWN");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rl_arrows_left.setBackgroundColor(Color.rgb(0x2a, 0x2a, 0x2a));
                                iv_arrows_left.setImageResource(R.mipmap.icon_left_white);
                            }
                        }, 100);

                        break;

                }
                return true;
            }
        });


        rl_arrows_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("点击效果ACTION_DOWN", "点击效果ACTION_DOWN");
                        //Toast.makeText(mContext,"单击ACTION_DOWN",Toast.LENGTH_LONG).show();
                        int index = mEditText.getSelectionStart();
                        if(index+1>mEditText.getText().length()){

                        }else{
                            Toast.makeText(mActivity, "下标:"+index+"",Toast.LENGTH_SHORT);
                            mEditText.setSelection(index+1);
                        }



                        rl_arrows_right.setBackgroundColor(Color.WHITE);
                        iv_arrows_right.setImageResource(R.mipmap.icon_right_black);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.e("点击效果ACTION_UP", "点击效果ACTION_DOWN");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rl_arrows_right.setBackgroundColor(Color.rgb(0x2a, 0x2a, 0x2a));
                                iv_arrows_right.setImageResource(R.mipmap.icon_right_white);
                            }
                        }, 100);

                        break;

                }
                return true;
            }
        });


        rl_delete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("点击效果ACTION_DOWN", "点击效果ACTION_DOWN");


                        Editable editable = mEditText.getText();
                        int start = mEditText.getSelectionStart();

                        rl_delete.setBackgroundColor(Color.WHITE);
                        iv_delete.setImageResource(R.mipmap.icon_keyboard_delete_black);

                        if (mEditText.hasFocus()) {
                            if (!TextUtils.isEmpty(editable)) {
                                if (start > 0) {
                                    editable.delete(start - 1, start);
                                }
                            }
                        }


                        break;

                    case MotionEvent.ACTION_UP:
                        Log.e("点击效果ACTION_UP", "点击效果ACTION_DOWN");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rl_delete.setBackgroundColor(Color.rgb(0x2a, 0x2a, 0x2a));
                                iv_delete.setImageResource(R.mipmap.icon_keyboard_delete_white);
                            }
                        }, 100);

                        break;

                }
                return true;
            }
        });


        rl_functionlinefree.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("点击效果ACTION_DOWN", "点击效果ACTION_DOWN");
                        //Toast.makeText(mContext,"单击ACTION_DOWN",Toast.LENGTH_LONG).show();
                        rl_functionlinefree.setBackgroundColor(Color.WHITE);
                        tv_functionlinefree.setTextColor(Color.rgb(0x2a, 0x2a, 0x2a));
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.e("点击效果ACTION_UP", "点击效果ACTION_DOWN");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rl_functionlinefree.setBackgroundColor(Color.rgb(0x2a, 0x2a, 0x2a));
                                tv_functionlinefree.setTextColor(Color.WHITE);
                            }
                        }, 100);

                        break;

                }
                return true;
            }
        });


        rl_switchKeyBoard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("点击效果ACTION_DOWN", "点击效果ACTION_DOWN");
                        //Toast.makeText(mContext,"单击ACTION_DOWN",Toast.LENGTH_LONG).show();
                        rl_switchKeyBoard.setBackgroundColor(Color.WHITE);
                        tv_sciencekeyboard.setTextColor(Color.rgb(0x2a, 0x2a, 0x2a));
                        iv_sciencekeyboard_arrows.setBackgroundResource(R.mipmap.icon_keyboard_jianpanxuanze_black_upward);
                        break;

                    case MotionEvent.ACTION_UP:
//                        Log.e("点击效果ACTION_UP","点击效果ACTION_DOWN");
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                rl_switchKeyBoard.setBackgroundColor(Color.rgb(0x2a,0x2a,0x2a));
//                                tv_sciencekeyboard.setTextColor(Color.WHITE);
//                                iv_sciencekeyboard_arrows.setBackgroundResource(R.mipmap.icon_keyboard_down_white);
//                            }
//                        },100);

                        int visibility = ll_KeyBoardGroup.getVisibility();
                        if (visibility == View.INVISIBLE) {
                            rl_switchKeyBoard.setBackgroundColor(Color.WHITE);
                            tv_sciencekeyboard.setTextColor(Color.rgb(0x2a, 0x2a, 0x2a));
                            iv_sciencekeyboard_arrows.setBackgroundResource(R.mipmap.icon_keyboard_jianpanxuanze_black_upward);
                            ll_KeyBoardGroup.setVisibility(View.VISIBLE);
                        } else {
                            rl_switchKeyBoard.setBackgroundColor(Color.rgb(0x2a, 0x2a, 0x2a));
                            tv_sciencekeyboard.setTextColor(Color.WHITE);
                            iv_sciencekeyboard_arrows.setBackgroundResource(R.mipmap.icon_keyboard_down_white);
                            ll_KeyBoardGroup.setVisibility(View.INVISIBLE);
                        }


                        break;

                }
                return true;
            }
        });


        rl_chemistrykeyboard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("点击效果ACTION_DOWN", "点击效果ACTION_DOWN");
                        //Toast.makeText(mContext,"单击ACTION_DOWN",Toast.LENGTH_LONG).show();

                        rl_chemistrykeyboard.setBackgroundColor(Color.rgb(0x2a, 0x2a, 0x2a));
                        tv_chemistrykeyboard.setTextColor(Color.WHITE);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.e("点击效果ACTION_UP", "点击效果ACTION_DOWN");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rl_chemistrykeyboard.setBackgroundColor(Color.WHITE);
                                tv_chemistrykeyboard.setTextColor(Color.rgb(0x2a, 0x2a, 0x2a));

                            }
                        }, 100);

                        break;

                }
                return true;
            }
        });


        rl_physicskeyboard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("点击效果ACTION_DOWN", "点击效果ACTION_DOWN");
                        //Toast.makeText(mContext,"单击ACTION_DOWN",Toast.LENGTH_LONG).show();


                        rl_physicskeyboard.setBackgroundColor(Color.rgb(0x2a, 0x2a, 0x2a));
                        tv_physicskeyboard.setTextColor(Color.WHITE);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.e("点击效果ACTION_UP", "点击效果ACTION_DOWN");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rl_physicskeyboard.setBackgroundColor(Color.WHITE);
                                tv_physicskeyboard.setTextColor(Color.rgb(0x2a, 0x2a, 0x2a));
                            }
                        }, 100);

                        break;

                }
                return true;
            }
        });


        rl_systemkeyboard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("点击效果ACTION_DOWN", "点击效果ACTION_DOWN");
                        //Toast.makeText(mContext,"单击ACTION_DOWN",Toast.LENGTH_LONG).show();
                        rl_systemkeyboard.setBackgroundColor(Color.rgb(0x2a, 0x2a, 0x2a));
                        tv_systemkeykeyboard.setTextColor(Color.WHITE);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.e("点击效果ACTION_UP", "点击效果ACTION_DOWN");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rl_systemkeyboard.setBackgroundColor(Color.WHITE);
                                tv_systemkeykeyboard.setTextColor(Color.rgb(0x2a, 0x2a, 0x2a));
                            }
                        }, 100);

                        break;

                }
                return true;
            }
        });


//        rl_chemistrykeyboard=(RelativeLayout) view.findViewById(R.id.rl_chemistrykeyboard);
//        rl_physicskeyboard=(RelativeLayout) view.findViewById(R.id.rl_physicskeyboard);
//        rl_systemkeyboard=(RelativeLayout) view.findViewById(R.id.rl_systemkeyboard);


    }

    @Override
    public void onItemClickView(int position) {
        Toast.makeText(mActivity, "position:" + position, Toast.LENGTH_LONG).show();
        keyCode_delect(position);
    }

    @Override
    public void onItemLongClickView(int position) {
        Toast.makeText(mActivity, "长按position:" + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongPress(int position) {
        Toast.makeText(mActivity, "小键盘:" + position, Toast.LENGTH_LONG).show();
    }


    int pageIndex=1;

    @Override
    public void onClick(View v) {
        Toast.makeText(mActivity, "id:" + v.getId(), Toast.LENGTH_LONG).show();
        switch (v.getId()) {


            case R.id.rl_arrows_left:
                int start = mEditText.getSelectionStart();
                Toast.makeText(mActivity, "下标:"+start+"",Toast.LENGTH_SHORT);
                mEditText.setSelection(start-1);


                break;

            case R.id.rl_arrows_right:
                int index = mEditText.getSelectionStart();
                Toast.makeText(mActivity, "下标:"+index+"",Toast.LENGTH_SHORT);
                mEditText.setSelection(index+1);



                break;



            case R.id.rl_more:

                GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 6);
                rlv_keyboard.setLayoutManager(gridLayoutManager);
                rlv_keyboard.setAdapter(mKeyBoardKeyMathPageSecondAdapter);
                mKeyBoardKeyMathPageSecondAdapter.setData(MATH_PAGE_SECOND);
                auto_ll_bottom2.setVisibility(View.VISIBLE);
                auto_ll_bottom1.setVisibility(View.GONE);
                pageIndex=2;
                break;

            case R.id.rl_more2:
                GridLayoutManager gridLayoutManager2 = new GridLayoutManager(mActivity, 7);
                rlv_keyboard.setLayoutManager(gridLayoutManager2);
                rlv_keyboard.setAdapter(mKeyBoardKeyAdapter);
                mKeyBoardKeyAdapter.setData(MATH_PAGE_FIRST);
                auto_ll_bottom1.setVisibility(View.VISIBLE);
                auto_ll_bottom2.setVisibility(View.GONE);
                pageIndex=1;

                break;


            case R.id.rl_key_pai:
                addText(Character.toString((char) '\uDF45'));

                break;

            case R.id.rl_key_jiajian:
                addText("±");

                break;

            case R.id.rl_key_budengyu:
                addText("≠");

                break;

            case R.id.rl_key_ciyate:
                addText("∞");

                break;

            case R.id.rl_key_shuxian:
                addText("|");

                break;

            case R.id.rl_key_baohan:
                addText("∈");

                break;
            case R.id.rl_key_jiaoji:
                addText("∪");

                break;

            case R.id.rl_key_bingji:
                addText("∩");

                break;


            case R.id.rl_key_e:
                addText("ℯ");

                break;

            case R.id.rl_key_jiajian2:
                addText("±");

                break;
            case R.id.rl_key_zuokuohao:
                addText("(");

                break;

            case R.id.rl_key_youkuohao:
                addText(")");

                break;

            case R.id.rl_key_zuozhongkuohao:
                addText("[");

                break;

            case R.id.rl_key_youzhongkuohao:
                addText("]");

                break;

            case R.id.rl_key_zuodakuohao:
                addText("{");

                break;

            case R.id.rl_key_youdakuohao:
                addText("}");

                break;





        }
    }


    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.top = space;
            // outRect.bottom = space;
//            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
//            if (parent.getChildLayoutPosition(view) %5==0) {
//                outRect.left = 0;
//            }

//            if(parent.getChildLayoutPosition(view)<=6){
//                outRect.top=0;
//            }else{
//
//            }

            //outRect.right=space;
        }

    }


    private int getNavigationBarHeight() {
        Resources resources = mActivity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }


    public static boolean navigationBarExist2(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics);
        }

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }


    private void keyCode_delect(int position) {

        Editable editable = mEditText.getText();
        int start = mEditText.getSelectionStart();
        String text="";
        if (mEditText.hasFocus()) {
            if(pageIndex==1) {
                text = positionTransformTextFirst(position);
            }else{
                text = positionTransformTextSecond(position);
            }
            editable.insert(start, text);
        }

    }



    private void addText(String text) {

        Editable editable = mEditText.getText();
        int start = mEditText.getSelectionStart();
        if (mEditText.hasFocus()) {

            editable.insert(start, text);
        }

    }


    String positionTransformTextFirst(int position) {
        String text = "null";

        switch (position) {
            case 0:
                text = "7";

                break;
            case 1:
                text = "8";

                break;

            case 2:
                text = "9";

                break;
            case 3:
                text = "+";

                break;
            case 4:
                text = ",";

                break;
            case 5:
                text = "⩾";

                break;
            case 6:
                text = "根号";

                break;
            case 7:
                text = "4";

                break;
            case 8:
                text = "5";

                break;
            case 9:
                text = "6";

                break;
            case 10:
                text = "-";

                break;
            case 11:
                text = ":";

                break;

            case 12:
                text = "⩽";

                break;
            case 13:
                text = "分号";

                break;

            case 14:
                text = "1";

                break;

            case 15:
                text = "2";

                break;

            case 16:
                text = "3";

                break;

            case 17:
                text = "×";

                break;

            case 18:
                text = "度数";

                break;

            case 19:
                text = ">";

                break;

            case 20:
                text = "平方";

                break;

            case 21:
                text = "0";

                break;

            case 22:
                text = ".";

                break;

            case 23:
                text = "=";

                break;

            case 24:
                text = "÷";

                break;

            case 25:
                text = "%";

                break;

            case 26:
                text = "<";

                break;

            case 27:
                text = "绝对值";

                break;

            case 28:
                text = "";

                break;

        }

        return text;
    }



    String positionTransformTextSecond(int position) {
        String text = "null";

        switch (position) {
            case 0:
                text = "sin";

                break;
            case 1:
                text = "∽";

                break;

            case 2:
                text = "≅";

                break;
            case 3:
                text = "\uD835\uDEFC";

                break;
            case 4:
                text = "\uD835\uDF0E";

                break;
            case 5:
                text = "\uD835\uDF5D";

                break;
            case 6:
                text = "cos";

                break;
            case 7:
                text = "∠";

                break;
            case 8:
                text = "⊥";

                break;
            case 9:
                text = "\uD835\uDEC3";

                break;
            case 10:
                text = "∵";

                break;
            case 11:
                text = "小弯";

                break;

            case 12:
                text = "tan";

                break;
            case 13:
                text = "⫽";

                break;

            case 14:
                text = "度数";
                break;

            case 15:
                text = "\uD835\uDFAC";

                break;

            case 16:
                text = "∴";

                break;

            case 17:
                text = "星星︎";

                break;

            case 18:
                text = "cot";
                break;

            case 19:
                text = "⟹";

                break;

            case 20:
                text = "⟺";

                break;

            case 21:
                text = "\uD835\uDF38";

                break;

            case 22:
                text = "△";

                break;

            case 23:
                text = "◻︎";

                break;

//            case 24:
//                text = "÷";
//
//                break;
//
//            case 25:
//                text = "%";
//
//                break;
//
//            case 26:
//                text = "<";
//
//                break;
//
//            case 27:
//                text = "绝对值";
//
//                break;
//
//            case 28:
//                text = "";
//
//                break;

        }

        return text;
    }
}
