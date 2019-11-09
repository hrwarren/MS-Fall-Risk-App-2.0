package com.example.bottomnavbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import static com.example.bottomnavbar.MainActivity.TAG;

public class ActionFourFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "ActionFourFragment.onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "ActionFourFragment.onCreateview()");
        View v = inflater.inflate(R.layout.action_four_fragment, container, false);
        return v;
    }

    public static Fragment newInstance() {
        return new ActionFourFragment();
    }
}
