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

    // Construct/initialize a notification manager and channel
    public NotificationManagerCompat notificationManager;
    public static final String CHANNEL_ID = "FALL RISK CHANNEL";
    protected void createNotificationChannel() {

        // if the current version is greater than the minimum acceptable (I think??)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Make a notification channel and give it an ID
            NotificationChannel fallchannel = new NotificationChannel(
                    CHANNEL_ID, "Fall Risk Channel", NotificationManager.IMPORTANCE_HIGH);

            // Let it vibrate the buzzer/motor/thing
            fallchannel.enableVibration(true);
            fallchannel.setDescription("This is channel allows for notifcations to occur in the event of a detected increased fall risk");

            //  Give it a manager
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(fallchannel);
        }
    }


    public final static String TAG = "BottomNavBar";

    // Initialize a member variable of the bottom nav view
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

                        // Open each fragment upon its button being pressed in the bottom nav bar
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

//// Create thread to handle fall risk event: add point to graph series, call notification
//    protected void onResume() {
//        super.onResume();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
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
        // Make a fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // Replace the current fragment with the new one
        fm.beginTransaction().replace(R.id.container, fragment).commit();

    }



}
