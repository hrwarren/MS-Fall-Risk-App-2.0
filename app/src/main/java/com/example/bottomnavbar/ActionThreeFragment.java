package com.example.bottomnavbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import static com.example.bottomnavbar.MainActivity.TAG;

public class ActionThreeFragment extends Fragment {
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
        return v;
    }

    public static Fragment newInstance() {
        return new ActionThreeFragment();
    }
}
