package com.learning.shilu.daggerdemo.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.Status;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.fragments.FirstFragment;
import com.learning.shilu.daggerdemo.fragments.SecondFragment;
import com.learning.shilu.daggerdemo.interfaces.OnFragmentInteractionListener;

public class StatusDetailActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private SharedPreferences sharedPreferences;

    private String[] listColors;
    private String[] listFeels;

    private RelativeLayout rlMain;
    private Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_detail);
        // setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra(Constants.STATUS_VALUE)){
            status = getIntent().getParcelableExtra(Constants.STATUS_VALUE);
        }
        rlMain = (RelativeLayout) findViewById(R.id.rl_main_container);

        listColors = getResources().getStringArray(R.array.color_list);
        listFeels = getResources().getStringArray(R.array.mood_list);

        sharedPreferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        switchFragment(sharedPreferences.getBoolean(Constants.KEY_STATUS, true));
    }

    private void switchFragment(boolean newStatus) {
        Fragment fragment;
        if (newStatus) {
            fragment = new FirstFragment(listFeels, status);
        } else {
            fragment = new SecondFragment(listFeels, status);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(boolean bool) {
        switchFragment(bool);
    }

    @Override
    public void onMoodSelection(int mPosition) {
        rlMain.setBackgroundColor(Color.parseColor(listColors[mPosition]));
    }

}
