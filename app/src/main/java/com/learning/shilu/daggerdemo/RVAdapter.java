package com.learning.shilu.daggerdemo;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learning.shilu.daggerdemo.interfaces.onClick;

import java.util.ArrayList;

/**
 * Created by Shilu Shrestha on 3/18/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private final ArrayList<Status> arraylist;
    private String[] listColors;

   public onClick onClickListener;

    public RVAdapter(ArrayList<Status> statusArrayList, String[] listColors) {
        this.arraylist = statusArrayList;
        this.listColors = listColors;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(DaggerDemoApplication.getContext()).inflate(R.layout.item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tvStatus.setText(arraylist.get(position).getStatus());
        holder.cvItem.setBackgroundColor(Color.parseColor(listColors[arraylist.get(position).getSelectedMood()]));
        holder.currentStatus = arraylist.get(position);
        holder.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

}
