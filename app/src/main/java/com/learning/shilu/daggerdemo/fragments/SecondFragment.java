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
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.configs.PrefConfig;
import com.learning.shilu.daggerdemo.configs.Status;
import com.learning.shilu.daggerdemo.interfaces.OnFragmentInteractionListener;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;

public class SecondFragment extends Fragment {
    private static final String STATUS_ID = "StatusId";

    @Inject
    PrefConfig prefConfig;

    @Inject
    Realm realm;

    @Inject
    @Named(Constants.Inject.LIST_FEELS)
    String[] listFeels;

    private OnFragmentInteractionListener mListener;
    private Status status;
    //private DaggerDemoSettings daggerDemoSettings;

    public static SecondFragment newInstance(String statusId) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(STATUS_ID, statusId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dagger
        DaggerDemoApplication.getComponent().inject(this);
        if (getArguments() != null) {
            String statusId = getArguments().getString(STATUS_ID);
            if (statusId != null) {
                status = realm.where(Status.class).equalTo("id", statusId).findFirst();
            }
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
            mListener.onFeelingSelection(status.getSelectedPosition());
        }

        // Using sharedpreferences
        // sharedPreferences = getContext().getSharedPreferences(Constants.PREF_NAME, getContext().MODE_PRIVATE);
        // tvStatus.setText(sharedPreferences.getString(Constants.KEY_TODAY_STATUS, ""));
        // tvCurrent.setText(sharedPreferences.getString(Constants.KEY_TODAY_STATUS, ""));

        // Using settings class
        // daggerDemoSettings = new DaggerDemoSettings();
        // textView.setText(daggerDemoSettings.getSharedPreferences(getContext()).getString("Today's Status", ""));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });

        return view;
    }

    private void onButtonPressed() {
        // daggerDemoSettings.putValue("NewStatus", true);
        // DaggerDemoApplication.getDaggerSettings().setStatus(true);
        // sharedPreferences.edit().putBoolean(Constants.KEY_STATUS, true);

        prefConfig.setStatus(true);
        if (mListener != null) {
            mListener.onFragmentInteraction(Constants.TO_FIRST_FRAGMENT, status.getId());
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
