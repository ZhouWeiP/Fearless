package com.exl.sciencekeyboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by 田楠 on 2016/9/7.
 * Description ：
 */
public class KeyBoardKeyViewHolder extends RecyclerView.ViewHolder{

    private ImageView ib_keyboarditem;
    private Context mContext;

    public KeyBoardKeyViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        AutoUtils.autoSize(itemView);
        ib_keyboarditem=(ImageView) itemView.findViewById(R.id.ib_keyboarditem);
    }

    public void bindData(int position,int pictureId) {
     ib_keyboarditem.setImageResource(pictureId);
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
}