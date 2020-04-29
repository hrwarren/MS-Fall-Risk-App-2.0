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

// This class opens the fragment that displays all of the logged fall alerts.

public class ActionThreeFragment extends Fragment {

    // Initialize the array list that will display the logged fall alerts
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

        // Open the action 3 fragment as our current view
        View v = inflater.inflate(R.layout.action_three_fragment, container, false);

        // Get a reference to the alert log's RecyclerView
        RecyclerView rvAlertLog = (RecyclerView) v.findViewById(R.id.rvAlertLog);


        // Right now, this generates a set number of alerts to populate the wireframe of this screen.
        // Real alerts will be logged once we can properly create them
        loggedAlerts = AlertLog.createAlertLog(20);

        // initialize an adapter
        AlertLogAdapter adapter = new AlertLogAdapter(loggedAlerts);

        // set the adapter as the adapter for this screen
        rvAlertLog.setAdapter(adapter);

        rvAlertLog.setLayoutManager(new LinearLayoutManager(getContext()));

        // return the action3 fragment view
        return v;
    }

    public static Fragment newInstance() {
        return new ActionThreeFragment();
    }
}
