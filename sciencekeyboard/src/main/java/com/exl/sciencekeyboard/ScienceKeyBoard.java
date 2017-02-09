package com.exl.sciencekeyboard;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwei on 17/1/18.
 */

public class ScienceKeyBoard implements KeyBoardOnItemClickView,View.OnClickListener{


     PopupWindow mPopupWindow;
     RelativeLayout rl_more;

     private Context mContext;
     private RecyclerView rlv_keyboard;
     private KeyBoardKeyAdapter mKeyBoardKeyAdapter;
     private static List<Integer> MATH_PAGE_FIRST=new ArrayList<Integer>();
    private static List<Integer> MATH_PAGE_SECOND=new ArrayList<Integer>();

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



     public ScienceKeyBoard(Context context){
         mContext=context;
         mPopupWindow=new PopupWindow(context);

         View view= LayoutInflater.from(context).inflate(R.layout.view_sciencekeyboard_content,null);
         initView(view);
         mPopupWindow.setContentView(view);
         mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
         mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
         mPopupWindow.setBackgroundDrawable(new BitmapDrawable());




     }


    public void show(View view){
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
    }

    private void initView(View view){
        rlv_keyboard=(RecyclerView) view.findViewById(R.id.rlv_keyboard);
        rl_more=(RelativeLayout) view.findViewById(R.id.rl_more);





        mKeyBoardKeyAdapter=new KeyBoardKeyAdapter(mContext,this);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,7);
        rlv_keyboard.setLayoutManager(gridLayoutManager);
        SpaceItemDecoration spacesItemDecoration=new SpaceItemDecoration(12);
        rlv_keyboard.addItemDecoration(spacesItemDecoration);

        rlv_keyboard.setAdapter(mKeyBoardKeyAdapter);
        mKeyBoardKeyAdapter.setData(MATH_PAGE_FIRST);
    }

    @Override
    public void onItemClickView(int position) {

    }

    @Override
    public void onItemLongClickView(int position) {

    }

    @Override
    public void onClick(View v) {

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
            outRect.top=space;
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
}
