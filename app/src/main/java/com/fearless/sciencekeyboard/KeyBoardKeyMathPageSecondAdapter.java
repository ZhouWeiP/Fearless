package com.fearless.sciencekeyboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fearless.water.R;

import java.util.List;

/**
 * Created by zhouwei on 16/8/19.
 */
public class KeyBoardKeyMathPageSecondAdapter extends RecyclerView.Adapter<KeyBoardKeyViewHolder> {

    public Context context;
    public List<Integer> ls;
    private KeyBoardOnItemClickView mKeyBoardOnItemClickView;


    public KeyBoardKeyMathPageSecondAdapter(Context context, KeyBoardOnItemClickView keyBoardOnItemClickView) {
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
                context).inflate(R.layout.item_keyboard_secondpage, parent,
                false),mKeyBoardOnItemClickView);

        return holder;
    }

    @Override
    public void onBindViewHolder(KeyBoardKeyViewHolder holder, final int position) {
        int pictureId = ls.get(position);
//        holder.getTv_obtainaward().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                obtainRewardView.obtainAward(rewardJob.getRewardJobId());
//            }
//        });
        holder.bindData(position,pictureId);
    }

    @Override
    public int getItemCount() {
        if(ls == null) return 0;
        return ls.size();
    }
}


