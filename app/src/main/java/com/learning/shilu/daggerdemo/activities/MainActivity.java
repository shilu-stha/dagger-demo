package com.learning.shilu.daggerdemo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.RVAdapter;
import com.learning.shilu.daggerdemo.Status;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.interfaces.onClick;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity implements onClick {

    @Inject
    SharedPreferences sharedPreferences;

//    @Inject
//    PreferencesModule preferencesModule;

    @Inject
    Context context;

    @Inject
    @Named(Constants.KEY_STATUS)
    boolean status;

    @Inject
    int position;

    @Inject
    ArrayList<Status> statusArrayList;

    @Inject
    @Named(Constants.LIST_COLORS)
    String[] listColors;

    @Inject
    @Named(Constants.LIST_FEELS)
    String[] listFeels;

    // private ArrayList<Status> statusArrayList = new ArrayList<>();
    // private String[] listColors;
    // private String[] listFeels;

    private TextView tvFeels;
    private TextView tvCurrentStatus;
    private RelativeLayout rlStatusContainer;
    private RecyclerView recyclerView;
    private RVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject dagger
        DaggerDemoApplication.getComponent().inject(this);

        // reference views
        tvCurrentStatus = (TextView) findViewById(R.id.tv_status);
        tvFeels = (TextView) findViewById(R.id.tv_mood);
        rlStatusContainer = (RelativeLayout) findViewById(R.id.rl_status_container);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // populate lists
        // listColors = getResources().getStringArray(R.array.color_list);
        // listFeels = getResources().getStringArray(R.array.mood_list);

        // reference saved value and update the UI
        // sharedPreferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        updateStatus(sharedPreferences.getBoolean(Constants.KEY_STATUS, true));

        // addDummyData();
        updateRecyclerView();
    }

    /*private void addDummyData() {
        statusArrayList.clear();
        statusArrayList.add(new Status("Discipline is just choosing between what you want now and what you want most.", 3));
        statusArrayList.add(new Status("A child of five would understand this. Send someone to fetch a child of five", 5));
        statusArrayList.add(new Status("You can do anything, but not everything.", 4));
        statusArrayList.add(new Status("We should be taught not to wait for inspiration to start a thing." +
                " Action always generates inspiration. Inspiration seldom generates action. ", 1));
        statusArrayList.add(new Status("You can do anything, but not everything.", 4));
        statusArrayList.add(new Status("I can accept failure, but I can't accept not trying.", 2));
        statusArrayList.add(new Status("There are many who dare not kill themselves for fear of what the neighbors will say. - Cyril Connolly", 5));
        statusArrayList.add(new Status("You can do anything, but not everything.", 4));
    }*/

    private void updateRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapter(context, statusArrayList, listColors);
        adapter.onClickListener = this;
        recyclerView.setAdapter(adapter);
    }

    /**
     * Update status preview view
     *
     * @param newStatus
     */
    private void updateStatus(boolean newStatus) {
        if (newStatus) {
            rlStatusContainer.setBackgroundColor(Color.parseColor("#616161"));
            tvCurrentStatus.setText("You haven't entered your status today!!");
            tvFeels.setVisibility(View.GONE);
        } else {
            //rlStatusContainer.setBackgroundColor(Color.parseColor(listColors[sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION, 0)]));
            //tvCurrentStatus.setText(sharedPreferences.getString(Constants.KEY_TODAY_STATUS, ""));
            //tvFeels.setVisibility(View.VISIBLE);
            //tvFeels.setText(listFeels[sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION, 0)]);

            rlStatusContainer.setBackgroundColor(Color.parseColor(listColors[sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION,0)]));
            tvCurrentStatus.setText(sharedPreferences.getString(Constants.KEY_TODAY_STATUS, ""));
            tvFeels.setVisibility(View.VISIBLE);
            tvFeels.setText(listFeels[sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION, 0)]);
        }
    }


    public void onClick(View view) {
        startActivity(new Intent(this, StatusDetailActivity.class));
    }

    @Override
    public void OnClick(View v, Status currentStatus) {
        Intent intent = new Intent(MainActivity.this, StatusDetailActivity.class);
        intent.putExtra(Constants.STATUS_VALUE, currentStatus);
        // Pass data object in the bundle and populate details activity.
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, v, "status_view");
        startActivity(intent, options.toBundle());
    }
}
