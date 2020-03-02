package com.example.mhealth_build;

import androidx.fragment.app.Fragment;

public class SensorConnectionActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SensorConnectionFragment();
    }

}
