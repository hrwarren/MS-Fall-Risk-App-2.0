package com.example.mhealth_build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mhealth_build.alertlog.ActionThreeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public NotificationManagerCompat notificationManager;
    public static final String CHANNEL_ID = "FALL RISK CHANNEL";
    protected void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel fallchannel = new NotificationChannel(
                    CHANNEL_ID, "Fall Risk Channel", NotificationManager.IMPORTANCE_HIGH);
            fallchannel.enableVibration(true);
            fallchannel.setDescription("This is channel allows for notifcations to occur in the event of a detected increased fall risk");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(fallchannel);
        }
    }


    public final static String TAG = "BottomNavBar";

    BottomNavigationView mBottomNavigationView;
    Fragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create notification  channel
        createNotificationChannel();
        notificationManager = NotificationManagerCompat.from(this);

        // Attach layout to activity
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

//
//    protected void onResume() {
//        super.onResume();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // listen for fall risk value reported by algorithm
//
//                //
//
//
//
//            }
//        }
//    }

    private void openFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, fragment).commit();

    }

    //this was the original; I think it just kept adding fragments on top of each other
//    private void openFragment(Fragment fragment) {
//        FragmentManager fm = getSupportFragmentManager(); //getFragmentManager vs getSupportFragmentManager
//        fm.beginTransaction().add(R.id.container, fragment).commit();
//    }



}
