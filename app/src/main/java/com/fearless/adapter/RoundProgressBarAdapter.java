package com.fearless.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fearless.customview.RoundProgressBar;
import com.fearless.water.R;

/**
 * Created by zhouwei on 16/10/9.
 */

public class RoundProgressBarAdapter extends BaseAdapter {

    private Context mContext;

    public RoundProgressBarAdapter(Context mContext){
        this.mContext=mContext;

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(mContext).inflate(R.layout.item_progress,null);
        RoundProgressBar rpb_prepare=((RoundProgressBar)convertView.findViewById(R.id.rpb_prepare));
        RoundProgressBar rpb_practice=((RoundProgressBar)convertView.findViewById(R.id.rpb_practice));
        RoundProgressBar rpb_consolidation=((RoundProgressBar)convertView.findViewById(R.id.rpb_consolidation));
        rpb_prepare.setText("预");
        rpb_practice.setText("作");
        rpb_consolidation.setText("固");

        rpb_prepare.setStatusImageResourceId(R.drawable.icon_first);
        rpb_practice.setStatusImageResourceId(R.drawable.icon_star_empty);
        rpb_consolidation.setStatusImageResourceId(R.drawable.load_view_net_not_connect);

        rpb_prepare.setProgress(100);
        rpb_practice.setProgress(50);
        rpb_consolidation.setProgress(30);

        ViewGroup.LayoutParams layoutParams=convertView.getLayoutParams();

        return convertView;
    }
}
