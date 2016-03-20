package com.learning.shilu.daggerdemo.activities;

import android.app.Activity;
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
import com.learning.shilu.daggerdemo.PreferencesModule;
import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.RVAdapter;
import com.learning.shilu.daggerdemo.Status;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.fragments.SecondFragment;
import com.learning.shilu.daggerdemo.interfaces.onClick;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity implements onClick {

//    private Button btnSwitch;
//    private TextView tv_current_status;

//    @Inject
//    SharedPreferences sharedPreferences;

    @Inject
    PreferencesModule preferencesModule;
    @Inject
    @Named(Constants.KEY_STATUS)
    boolean status;
    @Inject
    int position;


    private TextView tvMood;
    private TextView tvCurrentStatus;
    private RelativeLayout rlStatusContainer;
    private RecyclerView recyclerView;

    private SharedPreferences sharedPreferences;
    private String[] listColors;
    private String[] listFeels;
    private ArrayList<Status> statusArrayList = new ArrayList<>();
    public static Activity activity;
    private RVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject dagger
        DaggerDemoApplication.component().inject(this);

        activity = this;

        // reference views
        tvCurrentStatus = (TextView) findViewById(R.id.tv_status);
        tvMood = (TextView) findViewById(R.id.tv_mood);
        rlStatusContainer = (RelativeLayout) findViewById(R.id.rl_status_container);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // populate lists
        listColors = getResources().getStringArray(R.array.color_list);
        listFeels = getResources().getStringArray(R.array.mood_list);

        // reference saved value and update the UI
        sharedPreferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        updateStatus(sharedPreferences.getBoolean(Constants.KEY_STATUS, true));

        addDummyData();
        updateRecyclerView();
    }

    private void addDummyData() {
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
    }

//        sharedPreferences = getSharedPreferences("dagger_demo", MODE_PRIVATE);
//        switchFragment(sharedPreferences.getBoolean("NewStatus", true));

    //        switchFragment(DaggerDemoApplication.getDaggerSettings().getStatus());
//        switchFragment(sharedPreferences.getBoolean(Constants.KEY_STATUS, true));
    private void updateRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapter(statusArrayList, listColors);
        adapter.onClickListener = this;
        recyclerView.setAdapter(adapter);
        /*recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatusDetailActivity.class);
                // Pass data object in the bundle and populate details activity.
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, rlStatusContainer, "status_view");
                startActivity(intent, options.toBundle());
            }
        });*/
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
            tvMood.setVisibility(View.GONE);
        } else {
//            onMoodSelection(DaggerDemoApplication.getDaggerSettings().getSelectedPosition());
            System.out.println("MainActivity position " + position);

//            onMoodSelection(preferencesModule.getSelectedPos());
            /* FIXME position is not updated on every-time it is saved need to find a workaround for this */
            // onMoodSelection(position);
//            fragment = new SecondFragment(listMoods);
            rlStatusContainer.setBackgroundColor(Color.parseColor(listColors[sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION, 0)]));
            tvCurrentStatus.setText(sharedPreferences.getString(Constants.KEY_TODAY_STATUS, ""));
            tvMood.setVisibility(View.VISIBLE);
            tvMood.setText(listFeels[sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION, 0)]);
        }
    }


    public void onClick(View view) {
        startActivity(new Intent(this, StatusDetailActivity.class));
    }

    @Override
    public void OnClick(View v, Status currentStatus) {
        System.out.println("Status " + currentStatus.getStatus());

        Intent intent = new Intent(MainActivity.this, StatusDetailActivity.class);
        intent.putExtra(Constants.STATUS_VALUE, currentStatus);
        // Pass data object in the bundle and populate details activity.
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, v, "status_view");
        startActivity(intent, options.toBundle());
    }
}
