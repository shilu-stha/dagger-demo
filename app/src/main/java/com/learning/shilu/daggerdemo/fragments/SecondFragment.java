package com.learning.shilu.daggerdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.Status;
import com.learning.shilu.daggerdemo.configs.DaggerDemoSettings;
import com.learning.shilu.daggerdemo.configs.PrefConfig;
import com.learning.shilu.daggerdemo.interfaces.OnFragmentInteractionListener;

import javax.inject.Inject;

public class SecondFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String[] listFeels;
    private Status status;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Inject
    PrefConfig prefConfig;

//    @Inject
//    PreferencesModule preferencesModule;

//    @Inject
//    String todayStatus;

    private OnFragmentInteractionListener mListener;
    private DaggerDemoSettings daggerDemoSettings;

    public SecondFragment(String[] listFeels, Status status) {
        // Required empty public constructor
        this.listFeels = listFeels;
        if (status != null) {
            this.status = status;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dagger
        DaggerDemoApplication.getComponent().inject(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        TextView tvStatus = (TextView) view.findViewById(R.id.tv_status);
        TextView tvCurrent = (TextView) view.findViewById(R.id.tv_current_status);
        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.btn_change);

        if (status != null) {
            tvStatus.setText(listFeels[status.getSelectedPosition()]);
            tvCurrent.setText(status.getStatus());
        } else {
//            sharedPreferences = getContext().getSharedPreferences(Constants.PREF_NAME, getContext().MODE_PRIVATE);
//            tvStatus.setText(sharedPreferences.getString(Constants.KEY_TODAY_STATUS, ""));
//            tvCurrent.setText(sharedPreferences.getString(Constants.KEY_TODAY_STATUS, ""));

            tvStatus.setText(listFeels[prefConfig.getSelectedPosition()]);
            tvCurrent.setText(prefConfig.getTodayStatus(""));
        }
//        daggerDemoSettings = new DaggerDemoSettings();
//        textView.setText(daggerDemoSettings.getSharedPreferences(getContext()).getString("Today's Status", ""));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });

        return view;
    }

    private void onButtonPressed() {
//        daggerDemoSettings.putValue("NewStatus", true);
//        DaggerDemoApplication.getDaggerSettings().setStatus(true);

//        sharedPreferences.edit().putBoolean(Constants.KEY_STATUS, true);
        prefConfig.setStatus(true);
        if (mListener != null) {
            mListener.onFragmentInteraction(true);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
