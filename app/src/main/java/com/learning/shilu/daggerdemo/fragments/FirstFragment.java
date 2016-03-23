package com.learning.shilu.daggerdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.configs.Config;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.configs.PrefConfig;
import com.learning.shilu.daggerdemo.configs.Status;
import com.learning.shilu.daggerdemo.interfaces.OnFragmentInteractionListener;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;


public class FirstFragment extends Fragment {
    private static final String STATUS_ID = "StatusId";

    @Inject
    PrefConfig prefConfig;

    @Inject
    Realm realm;

    @Inject
    @Named(Constants.Inject.LIST_FEELS)
    String[] listFeels;

    private String statusId;
    private Status status = null;
    private int mPosition;
    private OnFragmentInteractionListener mListener;
    private AutoCompleteTextView etStatus;
    private EditText etCurrentStatus;
    private long date;

    public static FirstFragment newInstance(String statusId) {
        FirstFragment fragment = new FirstFragment();
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
            statusId = getArguments().getString(STATUS_ID);
            if (statusId != null) {
                status = realm.where(Status.class).equalTo("id", statusId).findFirst();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        etStatus = (AutoCompleteTextView) view.findViewById(R.id.et_status);
        etCurrentStatus = (EditText) view.findViewById(R.id.et_current_status);

        if (statusId != null) {
            etCurrentStatus.setText(status.getStatus());
            etStatus.setText(listFeels[status.getSelectedPosition()]);
        }

        mListener.onFeelingSelection(7);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listFeels);
        etStatus.setAdapter(adapter);

        etStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etStatus.showDropDown();
            }
        });

        etStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                updateBackground();
            }
        });

        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.btn_save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });

        return view;
    }

    private void updateBackground() {
        mListener.onFeelingSelection(mPosition);
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

    public void onButtonPressed() {
        if (!TextUtils.isEmpty(etStatus.getText().toString())) {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean(Constants.KEY_STATUS, false);
//            editor.putString(Constants.KEY_TODAY_STATUS, etCurrentStatus.getText().toString());
//            editor.putInt(Constants.KEY_SELECTED_POSITION, mPosition);
//            editor.commit();

//            DaggerDemoApplication.getDaggerSettings().setStatus(false);
//            DaggerDemoApplication.getDaggerSettings().setTodayStatus(etStatus.getText().toString());
//            DaggerDemoApplication.getDaggerSettings().setSelectedPosition(mPosition);

            date = System.currentTimeMillis();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (statusId == null) {
                        status = realm.createObject(Status.class);
                        status.setId(UUID.randomUUID().toString());
                        status.setDate(date);
                        status.setDateVal(Config.getDate(date));
                    }
                    status.setSelectedPosition(mPosition);
                    status.setStatus(etCurrentStatus.getText().toString());
                }
            });

            prefConfig.setStatus(false);
            //prefConfig.setTodayStatus(etCurrentStatus.getText().toString());
            //prefConfig.setSelectedPosition(mPosition);

            if (mListener != null) {
                mListener.onFragmentInteraction(Constants.TO_SECOND_FRAGMENT, status.getId());
            }
        }
    }
}
