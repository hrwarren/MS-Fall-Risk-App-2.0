package com.example.bottomnavbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "BottomNavBar";

    BottomNavigationView mBottomNavigationView;
    Fragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.navigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_one:
                                mFragment = ActionOneFragment.newInstance();
                                openFragment(mFragment);
                                break;
                            case R.id.action_two:
                                mFragment = ActionTwoFragment.newInstance();
                                openFragment(mFragment);
                                break;
                            case R.id.action_three:
                                mFragment = ActionThreeFragment.newInstance();
                                openFragment(mFragment);
                                break;
                            case R.id.action_four:
                                mFragment = ActionFourFragment.newInstance();
                                openFragment(mFragment);
                                break;
                        }
                        return true;
                    }
                });
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, fragment).commit();
    }

}
