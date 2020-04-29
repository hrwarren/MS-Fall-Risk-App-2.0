package com.example.mhealth_build.dash;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mhealth_build.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Random;

import static com.example.mhealth_build.MainActivity.TAG;

public class ActionTwoFragment extends Fragment {

    // Initialize graphs and graph variables
    PointsGraphSeries<DataPoint> series;
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private PointsGraphSeries<DataPoint> mSeries1;
    private double graph2LastXVal = 5d;

    // Trying to set up date format; we'll see
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "ActionTwoFragment.onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "ActionTwoFragment.onCreateview()");

        // Open the action 2 fragment as our current view, set it to visible
        View v = inflater.inflate(R.layout.action_two_fragment, container, false);
        v.setVisibility(View.VISIBLE);

        // Make a graph view for the 24-hr graph of fall risk
        GraphView graph1 = (GraphView) v.findViewById(R.id.graph_24hrs);
        // Add data to it
        mSeries1 = new PointsGraphSeries<>(generateData());
        graph1.addSeries(mSeries1);

        // Make graph1 pretty, with respectable axes, scrolling, and gridlines (or try to)
        graph1.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph1.getGridLabelRenderer().setNumHorizontalLabels(4);
        graph1.getGridLabelRenderer().setHorizontalAxisTitle("Hrs");
//        graph1.getViewport().setMinX(p.getTime());
        //graph1.getViewport().setMaxX(p.getTime());
        graph1.getViewport().setXAxisBoundsManual(true);
        Viewport viewport1 = graph1.getViewport();
        viewport1.setYAxisBoundsManual(true);
        viewport1.setMinY(0);
        viewport1.setMaxY(1.1);
        viewport1.setXAxisBoundsManual(true);
        viewport1.setScrollable(true);
        viewport1.scrollToEnd();
        viewport1.setScalable(true);

        // Make a graph view for the 1-wk graph of fall risk
        GraphView graph2 = (GraphView) v.findViewById(R.id.graph_1wk);
        // Add data to it
        series = new PointsGraphSeries();
        graph2.addSeries(series);
        // Make graph2 pretty, or try to
        Viewport viewport2 = graph2.getViewport();
        viewport2.setYAxisBoundsManual(true);
        viewport2.setMinY(0);
        viewport2.setMaxY(1.1);
        viewport2.setXAxisBoundsManual(true);
        viewport2.setScrollable(true);
        viewport2.scrollToEnd();
        viewport2.setScalable(true);

        // Make a graph view for the 1-mo graph of fall risk
        GraphView graph3 = (GraphView) v.findViewById(R.id.graph_1mo);
        // Add data to it
        series = new PointsGraphSeries();
        graph3.addSeries(series);
        // Make graph3 pretty, or try to
        Viewport viewport3 = graph3.getViewport();
        viewport3.setYAxisBoundsManual(true);
        viewport3.setMinY(0);
        viewport3.setMaxY(1.1);
        viewport3.setXAxisBoundsManual(true);
        viewport3.setScrollable(true);
        viewport3.scrollToEnd();
        viewport3.setScalable(true);

        // Make a graph view for the 1-yr graph of fall risk
        GraphView graph4 = (GraphView) v.findViewById(R.id.graph_1yr);
        // Add data to it
        series = new PointsGraphSeries();
        graph4.addSeries(series);
        // Make graph3 pretty, or try to
        Viewport viewport4 = graph4.getViewport();
        viewport4.setYAxisBoundsManual(true);
        viewport4.setMinY(0);
        viewport4.setMaxY(1.1);
        viewport4.setXAxisBoundsManual(true);
        viewport4.setScrollable(true);
        viewport4.scrollToEnd();
        viewport4.setScalable(true);

        // Bad dog, spit that out! Give me my view back!
        return v;

    }


    @Override
    // This is called whenever the fragment starts up, even if it isn't being created for the first time
    public void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                // Generate data for series1,
                mSeries1.resetData(generateData());
                //Delay to keep app from throwing a tantrum
                mHandler.postDelayed(this, 300);
            }
        };
        // Another delay to prevent tantrums
        mHandler.postDelayed(mTimer1, 300);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer1);
        super.onPause();
    }

    // This just randomly generates data for the graphs. It is a stand-in to work on displaying values
    // until we can actually feed the app sensor data
    private DataPoint[] generateData() {
        int count = 30;
        DataPoint[] values = new DataPoint[count];

        LocalTime currentTime = LocalTime.now();
//        public int get(TemporalField currentTime);


        for (int i = 0; i < count; i++) {
            Random mRand = new Random();
            double x = i;
            double f = mRand.nextDouble(); //*0.15+0.3;
            double y = Math.sin(i * f + 2) + mRand.nextDouble() * 0.3;
            DataPoint p = new DataPoint(x, f);
            values[i] = p;
        }

        return values;
    }


    // Return the action 2 fragment
    public static Fragment newInstance() {
        return new ActionTwoFragment();
    }
}
