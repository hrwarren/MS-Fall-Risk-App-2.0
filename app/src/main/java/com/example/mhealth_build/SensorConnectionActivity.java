package com.example.mhealth_build;

import androidx.fragment.app.Fragment;

public class SensorConnectionActivity extends SingleFragmentActivity {
    @Override
    // creates a fragment to hold the sensor connection activity/screen
    protected Fragment createFragment() {
        return new SensorConnectionFragment();
    }

}
