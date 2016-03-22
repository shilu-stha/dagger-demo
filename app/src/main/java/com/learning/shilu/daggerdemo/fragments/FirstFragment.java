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
import com.learning.shilu.daggerdemo.Status;
import com.learning.shilu.daggerdemo.configs.PrefConfig;
import com.learning.shilu.daggerdemo.interfaces.OnFragmentInteractionListener;

import javax.inject.Inject;


public class FirstFragment extends Fragment {
    private static final String SELECTED_POSITION = "SelectedPosition";
    private static String[] listFeels = new String[20];
    private Status status = null;

    private int mPosition;

    private OnFragmentInteractionListener mListener;
    private AutoCompleteTextView etStatus;
    private EditText etCurrentStatus;

    @Inject
    PrefConfig prefConfig;

//    @Inject
//    Realm realm;
    private long date;

    public FirstFragment(String[] listFeels, Status status) {
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
            mPosition = getArguments().getInt(SELECTED_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        etStatus = (AutoCompleteTextView) view.findViewById(R.id.et_status);
        etCurrentStatus = (EditText) view.findViewById(R.id.et_current_status);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
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

            Status status = new Status();
            status.setSelectedPosition(mPosition);
            status.setStatus(etCurrentStatus.getText().toString());
            status.setDate(date);

//            realm.beginTransaction();
//            realm.copyToRealm(status);
//            realm.commitTransaction();

            prefConfig.setStatus(false);
            prefConfig.setTodayStatus(etCurrentStatus.getText().toString());
            prefConfig.setSelectedPosition(mPosition);

            if (mListener != null) {
                mListener.onFragmentInteraction(status);
            }
        }
    }
}
