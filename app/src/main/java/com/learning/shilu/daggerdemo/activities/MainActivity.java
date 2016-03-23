package com.learning.shilu.daggerdemo.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.adapters.RVAdapter;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.configs.PrefConfig;
import com.learning.shilu.daggerdemo.configs.Status;
import com.learning.shilu.daggerdemo.interfaces.onClick;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements onClick {

    @Inject
    PrefConfig prefConfig;

    @Inject
    Context context;

//    @Inject
//    ArrayList<Status> statusArrayList;

    @Inject
    @Named(Constants.Inject.LIST_COLORS)
    String[] listColors;

    @Inject
    @Named(Constants.Inject.LIST_FEELS)
    String[] listFeels;

    @Inject
    Realm realm;

    @Inject
    @Named(Constants.Inject.TODAYS_DATE)
    String todaysDate;

    @Inject
    @Named(Constants.Inject.DUMMY_ENTRY)
    Boolean dummyEntry;

    // private ArrayList<Status> statusArrayList = new ArrayList<>();
    // private String[] listColors;
    // private String[] listFeels;

    private TextView tvFeels;
    private TextView tvCurrentStatus;
    private RelativeLayout rlStatusContainer;
    private RecyclerView recyclerView;
    private RVAdapter adapter;
    RealmResults<Status> statusRealmResults = null;
    RealmResults<Status> todayRealmResults;
    private Status todayStatus;

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

        // addDummyData();
        updateRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("TOday date "+todaysDate);

        todayRealmResults = realm.where(Status.class).equalTo("dateVal", todaysDate).findAll();
        if (todayRealmResults.size() != 0)
            todayStatus = todayRealmResults.first();

        checkDate();

        // reference saved value and update the UI
        // sharedPreferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        // updateStatus(sharedPreferences.getBoolean(Constants.KEY_STATUS, true));
        updateStatus();

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

        if (dummyEntry) {
            statusRealmResults = realm.where(Status.class).findAll();
            System.out.println("realm " + statusRealmResults.size());
        } else {
//            statusRealmResults = emptylist;
        }
        adapter = new RVAdapter(context, statusRealmResults, listColors);

        adapter.onClickListener = this;
        recyclerView.setAdapter(adapter);
    }

    /**
     * Update status preview view
     */
    private void updateStatus() {
        if (prefConfig.getStatus()) {
            rlStatusContainer.setBackgroundColor(Color.parseColor("#616161"));
            tvCurrentStatus.setText("You haven't entered your status today!!");
            tvFeels.setVisibility(View.GONE);
        } else {
            //rlStatusContainer.setBackgroundColor(Color.parseColor(listColors[sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION, 0)]));
            //tvCurrentStatus.setText(sharedPreferences.getString(Constants.KEY_TODAY_STATUS, ""));
            //tvFeels.setVisibility(View.VISIBLE);
            //tvFeels.setText(listFeels[sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION, 0)]);

            //rlStatusContainer.setBackgroundColor(Color.parseColor(listColors[prefConfig.getSelectedPosition()]));
            // tvCurrentStatus.setText(prefConfig.getTodayStatus(""));
            //tvFeels.setVisibility(View.VISIBLE);
            //tvFeels.setText(listFeels[prefConfig.getSelectedPosition()]);

            // Using Realm db
            rlStatusContainer.setBackgroundColor(Color.parseColor(listColors[todayStatus.getSelectedPosition()]));
            tvCurrentStatus.setText(todayStatus.getStatus());
            tvFeels.setText(listFeels[todayStatus.getSelectedPosition()]);
            tvFeels.setVisibility(View.VISIBLE);
        }
    }


    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, StatusDetailActivity.class);
//        String date = Status.getDate(System.currentTimeMillis());
//        Status status = realm.where(Status.class).equalTo("dateVal", date).findFirst();
        if (prefConfig.getStatus() != null) {
            Status status = new Status();
            status.setDate(System.currentTimeMillis());
            status.setStatus(prefConfig.getTodayStatus(""));
            status.setSelectedPosition(prefConfig.getSelectedPosition());
//            intent.putExtra(Constants.Inject.STATUS_ID, status);
            startActivity(intent);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void OnClick(View v, String id) {
        Intent intent = new Intent(MainActivity.this, StatusDetailActivity.class);
        intent.putExtra(Constants.Inject.STATUS_ID, id);
        // Pass data object in the bundle and populate details activity.
        Pair<View, String> p1 = Pair.create(v, "status_view");
        Pair<View, String> p2 = Pair.create(v, "status_msg");

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1, p2);
        startActivity(intent, options.toBundle());
    }

    private void checkDate() {
        if (todayRealmResults.size() == 0) {
            prefConfig.setStatus(true);
            System.out.println("Equal");
        }
    }
}
