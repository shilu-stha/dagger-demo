package com.learning.shilu.daggerdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.PreferencesModule;
import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.interfaces.OnFragmentInteractionListener;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    private static final String SELECTED_POSITION = "SelectedPosition";
    private static String[] listMoods = new String[20];

    private int mPosition;

    private OnFragmentInteractionListener mListener;
    private AutoCompleteTextView etStatus;

    @Inject
    PreferencesModule preferencesModule;

    public FirstFragment(String[] listMoods) {
        // Required empty public constructor
        this.listMoods = listMoods;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @param list     Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(int position, String[] list) {
        FirstFragment fragment = new FirstFragment(list);
        Bundle args = new Bundle();
        args.putInt(SELECTED_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inject dagger
        DaggerDemoApplication.component().inject(this);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listMoods);
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

        Button btn = (Button) view.findViewById(R.id.btn_save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });

        return view;
    }

    private void updateBackground() {
//        DaggerDemoApplication.getDaggerSettings().setSelectedPosition(mPosition);

        System.out.println("Selected position " + mPosition);

        preferencesModule.setSelectedPosition(mPosition);
        mListener.onMoodSelection(mPosition);
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
//            SharedPreferences sharedPreferences = getContext().getSharedPreferences("dagger_demo", getContext().MODE_PRIVATE);
//            sharedPreferences.edit().putBoolean("NewStatus", false).commit();
//            sharedPreferences.edit().putString("Today's Status", etStatus.getText().toString()).commit();

//            DaggerDemoApplication.getDaggerSettings().setStatus(false);
//            DaggerDemoApplication.getDaggerSettings().setTodayStatus(etStatus.getText().toString());

            preferencesModule.setStatus(false);
            preferencesModule.setTodayStatus(etStatus.getText().toString());

            if (mListener != null) {
                mListener.onFragmentInteraction(false);
            }
        }
    }
}
