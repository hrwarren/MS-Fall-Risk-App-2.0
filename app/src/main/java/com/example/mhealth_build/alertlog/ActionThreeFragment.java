package com.example.mhealth_build.alertlog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mhealth_build.R;

import java.util.ArrayList;

import static com.example.mhealth_build.MainActivity.TAG;

public class ActionThreeFragment extends Fragment {

    ArrayList<AlertLog> loggedAlerts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "ActionThreeFragment.onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "ActionThreeFragment.onCreateview()");
        View v = inflater.inflate(R.layout.action_three_fragment, container, false);

        // Get a reference to RecyclerView
        RecyclerView rvAlertLog = (RecyclerView) v.findViewById(R.id.rvAlertLog);


        // Do I need a layout manager?

        loggedAlerts = AlertLog.createAlertLog(20);

        AlertLogAdapter adapter = new AlertLogAdapter(loggedAlerts);

        rvAlertLog.setAdapter(adapter);

        rvAlertLog.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
        //return rvAlertLog;
    }

    public static Fragment newInstance() {
        return new ActionThreeFragment();
    }
}
