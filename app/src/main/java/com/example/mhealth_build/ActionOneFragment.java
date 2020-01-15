package com.example.mhealth_build;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.mhealth_build.MainActivity.TAG;

public class ActionOneFragment extends Fragment {

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
        View v = inflater.inflate(R.layout.action_one_fragment, container, false);

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


        return v;
    }

    public static Fragment newInstance() {
        return new ActionOneFragment();
    }
}
