package com.fearless.sciencekeyboard;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.fearless.water.R;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoUtils;


/**
 * Created by 田楠 on 2016/9/7.
 * Description ：
 */
public class KeyBoardKeyViewHolder extends RecyclerView.ViewHolder{
    private AutoRelativeLayout auto_rl_root;
    private ImageView ib_keyboarditem;
    private KeyBoardOnItemClickView mKeyBoardOnItemClickView;
    private Context mContext;

    public KeyBoardKeyViewHolder(Context context, View itemView, KeyBoardOnItemClickView keyBoardOnItemClickView) {
        super(itemView);
        mContext = context;
        AutoUtils.autoSize(itemView);
        auto_rl_root=(AutoRelativeLayout) itemView.findViewById(R.id.auto_rl_root);
        ib_keyboarditem=(ImageView) itemView.findViewById(R.id.ib_keyboarditem);
        mKeyBoardOnItemClickView=keyBoardOnItemClickView;
    }

    public void bindData(final int position, int pictureId) {

     ib_keyboarditem.setImageResource(pictureId);
        auto_rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mKeyBoardOnItemClickView.onItemClickView(position);
                mKeyBoardOnItemClickView.onItemLongClickView(position);
            }
        });

        ib_keyboarditem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                long start;
                long end;
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        start=System.currentTimeMillis();

                        break;




                }

                return false;
            }
        });
    }

    public RecycleItemClick getRecycleItemClick() {
        return recycleItemClick;
    }

    public void setRecycleItemClick(RecycleItemClick recycleItemClick) {
        this.recycleItemClick = recycleItemClick;
    }



    private RecycleItemClick recycleItemClick;
    public  interface RecycleItemClick{
        void onItemClick();
    }


    public static class CountTimeThread extends  Thread{

        private View view;
        long start=0;
        long end=0;

        public CountTimeThread(View view, int position, Handler handler){
            this.view=view;
        }

        @Override
        public void run() {

            start=System.currentTimeMillis();
            while (!((end-start)>=2000)){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                end=System.currentTimeMillis();

            }

        }
    }
}