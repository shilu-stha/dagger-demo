package com.learning.shilu.daggerdemo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.configs.Status;
import com.learning.shilu.daggerdemo.interfaces.onClick;

import io.realm.RealmResults;

/**
 * Created by Shilu Shrestha on 3/18/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final RealmResults<Status> realmResults;
    private final Context context;
    private String[] listColors;

    public onClick onClickListener;

    public RVAdapter(Context context, RealmResults<Status> statusArrayList, String[] listColors) {
        this.realmResults = statusArrayList;
        this.listColors = listColors;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        System.out.println("Realm ID " + realmResults.get(position).getId());
        holder.tvStatus.setText(realmResults.get(position).getStatus());
        holder.cvItem.setBackgroundColor(Color.parseColor(listColors[realmResults.get(position).getSelectedPosition()]));
        holder.currentStatus = realmResults.get(position);
        holder.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }

}
