package com.fearless.sciencekeyboard;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fearless.water.R;

import java.util.List;

/**
 * Created by zhouwei on 16/8/19.
 */
public class KeyBoardKeyAdapter extends RecyclerView.Adapter<KeyBoardKeyViewHolder> {

    public Context context;
    public List<Integer> ls;
    private KeyBoardOnItemClickView mKeyBoardOnItemClickView;
    private View.OnClickListener mOnClickListener;
    Handler LongPressHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int position=msg.what;
            Toast.makeText(context,"长按:"+position,Toast.LENGTH_LONG).show();
            mKeyBoardOnItemClickView.onItemLongPress(position);
        }
    };

    public KeyBoardKeyAdapter(Context context, KeyBoardOnItemClickView keyBoardOnItemClickView) {
        this.context=context;
        this.mKeyBoardOnItemClickView=keyBoardOnItemClickView;
    }

    public void setData(List<Integer> ls) {
        this.ls = ls;
        notifyDataSetChanged();
    }

    @Override
    public KeyBoardKeyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        KeyBoardKeyViewHolder holder = new KeyBoardKeyViewHolder(context,LayoutInflater.from(
                context).inflate(R.layout.item_keyboard_firstpage, parent,
                false),mKeyBoardOnItemClickView);

        return holder;
    }

    @Override
    public void onBindViewHolder(KeyBoardKeyViewHolder holder, final int position) {
        int pictureId = ls.get(position);

        holder.bindData(position,pictureId);
    }

    @Override
    public int getItemCount() {
        if(ls == null) return 0;
        return ls.size();
    }
}


