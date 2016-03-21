package com.learning.shilu.daggerdemo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.learning.shilu.daggerdemo.interfaces.onClick;

/**
 * Created by Shilu Shrestha on 3/18/2016.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvStatus;
    CardView cvItem;

    Status currentStatus;
    onClick onClickListener;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
        cvItem = (CardView) itemView.findViewById(R.id.card_view);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("Status "+currentStatus.getStatus());
        onClickListener.OnClick(v, currentStatus);
    }
}