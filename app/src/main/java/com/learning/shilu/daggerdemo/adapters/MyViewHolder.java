package com.learning.shilu.daggerdemo.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.configs.Status;
import com.learning.shilu.daggerdemo.interfaces.onClickItem;

/**
 * Created by Shilu Shrestha on 3/18/2016.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    final TextView tvStatus;
    final CardView cvItem;
    Status currentStatus;
    onClickItem onClickItemListener;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
        cvItem = (CardView) itemView.findViewById(R.id.card_view);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onClickItemListener.onClickItem(v, currentStatus.getId());
    }
}
