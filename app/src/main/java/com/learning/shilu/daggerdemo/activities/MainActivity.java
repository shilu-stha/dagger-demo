package com.learning.shilu.daggerdemo.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.fragments.FirstFragment;
import com.learning.shilu.daggerdemo.fragments.SecondFragment;
import com.learning.shilu.daggerdemo.interfaces.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private Button btnSwitch;
    private TextView tv_current_status;
    private SharedPreferences sharedPreferences;

    private LinearLayout llMain;

    private String[] listColors;
    private String[] listMoods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listColors = getResources().getStringArray(R.array.color_list);
        listMoods = getResources().getStringArray(R.array.mood_list);

//        tv_current_status = (TextView) findViewById(R.id.tv_current_status);
//        btnSwitch = (Button) findViewById(R.id.btn_switch_fragment);

        llMain = (LinearLayout) findViewById(R.id.ll_main_container);

//        sharedPreferences = getSharedPreferences("dagger_demo", MODE_PRIVATE);
//        switchFragment(sharedPreferences.getBoolean("NewStatus", true));

        switchFragment(DaggerDemoApplication.getDaggerSettings().getStatus());
    }

    private void switchFragment(boolean newStatus) {
        Fragment fragment;
        if (newStatus) {
            fragment = new FirstFragment(listMoods);
        } else {
            onMoodSelection(DaggerDemoApplication.getDaggerSettings().getSelectedPosition());
            fragment = new SecondFragment(listMoods);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(boolean bool) {
//        tv_current_status.setText(sharedPreferences.getString("Today's Status", ""));
//        tv_current_status.setText(DaggerDemoApplication.getDaggerSettings().getTodayStatus());
        switchFragment(bool);
    }

    @Override
    public void onMoodSelection(int mPosition) {
        llMain.setBackgroundColor(Color.parseColor(listColors[mPosition]));
    }
}
