package com.example.mhealth_build;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.mhealth_build.sensorConnection.SensorConnectionActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static com.example.mhealth_build.MainActivity.TAG;

public class ActionOneFragment extends Fragment implements View.OnClickListener {

    // initialize member variable of fragment
    private Fragment mFragment = null;

    // initialize graphs and graph variables
    LineGraphSeries<DataPoint> series;

    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private LineGraphSeries<DataPoint> mHrSeries;
    private double graph2LastXVal = 5d;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "ActionOneFragment.onCreate()");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "ActionOneFragment.onCreateview()");

        // open the action 1 fragment as our current view
        View v = inflater.inflate(R.layout.action_one_fragment, container, false);

        // listener for button to lead to sensor connection
        Button btn = (Button) v.findViewById(R.id.sensorBtn);
        btn.setOnClickListener(this); // calling onClick() method

        // display the fall risk graph
        GraphView hrGraph = (GraphView) v.findViewById(R.id.graph_1hr);
        series = new LineGraphSeries();

        hrGraph.addSeries(series);
        Viewport viewport3 = hrGraph.getViewport();
        viewport3.setYAxisBoundsManual(true);
        viewport3.setMinY(0);
        viewport3.setMaxY(1.1);
        viewport3.setXAxisBoundsManual(true);
        viewport3.setScrollable(true);
        viewport3.scrollToEnd();
        viewport3.setScalable(true);

        // return the current view
        return v;
    }

    @Override
    // This is the listener for the button that opens to the sensor connection screen
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sensorBtn:
                // open a new intent for the sensor connection activity
                Intent intent = new Intent(getActivity(), SensorConnectionActivity.class);
                startActivity(intent);
                break;

        }
    }

    // Return the action 1 fragment
    public static Fragment newInstance () { //should this be static or not?
        return new ActionOneFragment();
    }

//    private void openFragment (Fragment fragment){
//        FragmentManager fm = getFragmentManager(); //should this be getSupportFragmentManager?
//        fm.beginTransaction().add(R.id.container, fragment).commit();
//    }

}
