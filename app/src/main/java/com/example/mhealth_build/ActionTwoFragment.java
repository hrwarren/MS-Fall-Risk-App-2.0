package com.example.mhealth_build;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalField;
import java.util.Random;

import static com.example.mhealth_build.MainActivity.TAG;

public class ActionTwoFragment extends Fragment {

    PointsGraphSeries<DataPoint> series;

    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private PointsGraphSeries<DataPoint> mSeries1;
    private double graph2LastXVal = 5d;

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
        View v = inflater.inflate(R.layout.action_two_fragment, container, false);
        v.setVisibility(View.VISIBLE);

        GraphView graph1 = (GraphView) v.findViewById(R.id.graph_24hrs);
        mSeries1 = new PointsGraphSeries<>(generateData());
        graph1.addSeries(mSeries1);

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

        GraphView graph2 = (GraphView) v.findViewById(R.id.graph_1wk);
        series = new PointsGraphSeries();
        graph2.addSeries(series);
        Viewport viewport2 = graph2.getViewport();
        viewport2.setYAxisBoundsManual(true);
        viewport2.setMinY(0);
        viewport2.setMaxY(1.1);
        viewport2.setXAxisBoundsManual(true);
        viewport2.setScrollable(true);
        viewport2.scrollToEnd();
        viewport2.setScalable(true);

        GraphView graph3 = (GraphView) v.findViewById(R.id.graph_1mo);
        series = new PointsGraphSeries();
        graph3.addSeries(series);
        Viewport viewport3 = graph3.getViewport();
        viewport3.setYAxisBoundsManual(true);
        viewport3.setMinY(0);
        viewport3.setMaxY(1.1);
        viewport3.setXAxisBoundsManual(true);
        viewport3.setScrollable(true);
        viewport3.scrollToEnd();
        viewport3.setScalable(true);

        return v;

    }

//    @Override
//    public void onAttach(Activity, activity) {
//        super.onAttach(activity);
//        ((MainActivity) activity).onSectionAttached(getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
//    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                mSeries1.resetData(generateData());
                mHandler.postDelayed(this, 300);
            }
        };
        mHandler.postDelayed(mTimer1, 300);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer1);
        super.onPause();
    }

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



    public static Fragment newInstance() {
        return new ActionTwoFragment();
    }
}
