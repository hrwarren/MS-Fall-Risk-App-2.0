package com.example.mhealth_build;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import static com.example.mhealth_build.MainActivity.TAG;

public class ActionOneFragment extends Fragment {
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
        return v;
    }

    public static Fragment newInstance() {
        return new ActionOneFragment();
    }
}
