package com.example.mhealth_build.sensorConnection;
// This fragment handles the connection with the bluetooth devices and communicates with the array adapter where they are kept track of

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.mhealth_build.MainActivity;
import com.example.mhealth_build.R;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.mbientlab.metawear.AsyncDataProducer;
import com.mbientlab.metawear.MetaWearBoard;
import com.mbientlab.metawear.Route;
import com.mbientlab.metawear.android.BtleService;
import com.mbientlab.metawear.data.Acceleration;
import com.mbientlab.metawear.module.Accelerometer;
import com.mbientlab.metawear.module.AccelerometerBosch;
import com.mbientlab.metawear.module.AccelerometerMma8452q;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bolts.Capture;
import bolts.Continuation;
import bolts.Task;



public class SensorConnectionFragment extends Fragment implements ServiceConnection, View.OnClickListener{

    // Initialize map that contains each bluetooth device as well as bind bluetooth service
    private final HashMap<DeviceState, MetaWearBoard> stateToBoards;
    private BtleService.LocalBinder binder;
    private ConnectedDeviceAdapter connectedDevicesAdapter = null; //formerly connectedDevices
    private ArrayList<DeviceState> connectedDevices;
    private RecyclerView mConnectedDevicesView;
    private ConnectedDeviceAdapter mAdapter;

    Fragment mFragment = null;

    public SensorConnectionFragment() {
        stateToBoards = new HashMap<>();
    }

    // For parsing accelerometer data within method "add new device"
    private String s;


    // All the bluetooth content was commented out by Dr. Jason Hibbeler, who has been helping me
    // with this project. If it doesn't go here though, I'm not sure where it goes.

/*-------------- jdh -------->
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("metawear","MAF: View Created");
        super.onCreate(savedInstanceState);
        Activity owner= getActivity();

        // bind bluetooth service
        owner.getApplicationContext().bindService(new Intent(owner, BtleService.class), this, Context.BIND_AUTO_CREATE);
    }
-------------- jdh ---------*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("JDH", "SensorConnectionFragment.onCreateView()");
        View view = inflater.inflate(R.layout.connector_fragment, container, false);

        Button back_btn = (Button) view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this); // calling onClick() method

        mConnectedDevicesView = view.findViewById(R.id.devices_recycler_view);
        mConnectedDevicesView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setRetainInstance(true);
        updateUI();
        return view;
    }


    private void updateUI() {
        DeviceStore deviceStore = DeviceStore.get(getActivity());
        List<DeviceState> devices = deviceStore.getDevices();

        if (mAdapter == null) {
            mAdapter = new ConnectedDeviceAdapter(devices);
            mAdapter.setContext(getActivity());
            mConnectedDevicesView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    // Unbind btle service on app closure or back navigation
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApplicationContext().unbindService(this);
    }


    // Add new device method includes connection to device, data route setup, and reconnection
    public void addNewDevice(BluetoothDevice btDevice) {
        final DeviceState newDeviceState= new DeviceState(btDevice);
        final MetaWearBoard newBoard= binder.getMetaWearBoard(btDevice);
        Log.i("metawear","MAF: Atempting to connect to  "+newDeviceState);

        // For progress bar
        newDeviceState.connecting = true;

        // Add device to connected device adapter class as well as add to hashmap
        connectedDevices.add(newDeviceState); //adding to array, not adapter right now
        stateToBoards.put(newDeviceState, newBoard);

        // initalize captures
        final Capture<AsyncDataProducer> orientCapture = new Capture<>();
        final Capture<Accelerometer> accelCapture = new Capture<>();


        // On unexpected disconnect, attempt to reconnect to device
        newBoard.onUnexpectedDisconnect(status -> {
            Log.i("metawear","MAF: Unexpected Disconnect Detected");
            Log.i("metawear","MAF: Connection lost, reconnecting to " + btDevice.getAddress());
            newBoard.connectAsync().continueWithTask(task -> task.isCancelled() || !task.isFaulted() ? task : SensorConnectionFragment.reconnect(newBoard))
                    .continueWith((Continuation<Void, Void>) task -> {

                        if (!task.isCancelled()) {
                            Log.i("metawear","MAF: Successful reconnection to: "+ btDevice.getAddress());

                        } else {
                            Log.i("metawear","MAF: Successful reconnection to: "+ btDevice.getAddress());
                        }

                        return null;
                    });
        });


        // Inital connection established:
        newBoard.connectAsync().onSuccessTask(task -> {
            Log.i("metawear","MAF: Device succesfully connected: " + btDevice.getAddress());

            getActivity().runOnUiThread(() -> {
                newDeviceState.connecting= false;
                // connectedDevices.notifyDataSetChanged();
            });


            // Configure accelerometer on board device
            final Accelerometer accelerometer = newBoard.getModule(Accelerometer.class);
            accelerometer.configure()
                    .odr(32.5f)       // Set sampling frequency to 25Hz, or closest valid ODR
                    .range(4f)      // Set data range to +/-4g, or closet valid range
                    .commit();

            Log.i("metawear", "MAF: Accel frequency = " + accelerometer.getOdr());
            Log.i("metawear", "MAF: Accel range = " + accelerometer.getRange());

            // set capture
            accelCapture.set(accelerometer);

            // Following code is necessary to configure the accelerometer to stream (not entirely sure why)
            final AsyncDataProducer orientation;
            if (accelerometer instanceof AccelerometerBosch) {
                orientation = ((AccelerometerBosch) accelerometer).acceleration();
            } else {
                orientation = ((AccelerometerMma8452q) accelerometer).acceleration();
            }
            orientCapture.set(orientation);

            // orienation is actually acceleration values, set up data stream:
            return orientation.addRouteAsync(source -> source.stream((data, env) -> {
                getActivity().runOnUiThread(() -> {

                    // parse accel values to get x, y, z components
                    s = data.value(Acceleration.class).toString();
                    newDeviceState.xVal = s.substring(4,10);
                    newDeviceState.yVal = s.substring(15,21);
                    newDeviceState.zVal = s.substring(27,32);

                    // connectedDevices.notifyDataSetChanged();

                });
            }));

        }).continueWith((Continuation<Route, Void>) task -> {
            // if unable to establish connection, remove from adapter and in turn from screen layout
            if (task.isFaulted()) {
                if (!newBoard.isConnected()) {
                    getActivity().runOnUiThread(() -> connectedDevices.remove(newDeviceState));
                } else {
                    // Not sure if sensor_list is what's needed here; originally activity_main_layout
                    Snackbar.make(getActivity().findViewById(R.id.sensor_list), task.getError().getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
                    newBoard.tearDown();
                    newBoard.disconnectAsync().continueWith((Continuation<Void, Void>) task1 -> {
                        connectedDevices.remove(newDeviceState);
                        return null;
                    });
                }
            } else {
                // Otherwise connection established, begin accel stream
                orientCapture.get().start();
                accelCapture.get().start();
            }
            return null;

        });
    };


/*--------------- jdh -------------------->
    // Create fragment view, called once
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //jdh connectedDevicesAdapter= new ConnectedDeviceAdapter(getActivity(), R.id.sensor_list);
        // connectedDevices.setNotifyOnChange(true);
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.connector_fragment, container, false); //not sure connector_fragment is what goes there!!!
        RecyclerView rvConnnectedDevices = (RecyclerView) view.findViewById(R.id.connected_devices);
        return view;
    }
---------------- jdh ------------------*/

/*-------------- jdh -------------------?
    // once view is created, can be called multiple times
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView connectedDevicesView= view.findViewById(R.id.connected_devices); //originally ListView, that's what's breaking everything
        connectedDevicesView.setAdapter(connectedDevicesAdapter);

        Log.i("JDH", "onViewCreated() in SensorConnectionFragment");

        // if you click on item, it will vibrate:
        connectedDevicesView.setOnItemClickListener((parent, view1, position, id) -> {
            Log.i("metawear","I've been clicked!");
            DeviceState current= connectedDevices.getItem(position);
            final MetaWearBoard newBoard= stateToBoards.get(current);

            newBoard.getModule(Haptic.class).startMotor(100.f, (short) 1000);
            newBoard.getModule(Haptic.class).startMotor(50.f, (short) 1000);
            newBoard.getModule(Haptic.class).startMotor(100.f, (short) 1000);
            newBoard.getModule(Haptic.class).startMotor(50.f, (short) 1000);
            newBoard.getModule(Haptic.class).startMotor(100.f, (short) 1000);
            newBoard.getModule(Haptic.class).startMotor(50.f, (short) 1000);
            newBoard.getModule(Haptic.class).startMotor(100.f, (short) 1000);
            newBoard.getModule(Haptic.class).startMotor(50.f, (short) 1000);

        });
----------------- jdh ------------*/

/*-------------- jdh -------------->
        // if you click on the item and hold for a while, it will disconnect, probably don't need this code, but also helpful if you connect to the wrong board by accident
        connectedDevicesView.setOnItemLongClickListener((parent, view1, position, id) -> {
            DeviceState current= connectedDevices.getItem(position);
            final MetaWearBoard selectedBoard= stateToBoards.get(current);
            Accelerometer accelerometer = selectedBoard.getModule(Accelerometer.class);
            accelerometer.stop();
            selectedBoard.tearDown();
            selectedBoard.getModule(Debug.class).disconnectAsync();
            connectedDevices.remove(current);
            return false;
        });

    }
----------------- jdh ---------------*/


    // necessary to establish bluetooth service connection
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder= (BtleService.LocalBinder) service;
        Log.i("metawear","MAF: Bluetooth service connected");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        // What to actually do when bluetooth service is disconnected?
    }

    // method called for reconnection
    public static Task<Void> reconnect(final MetaWearBoard board) {
        return board.connectAsync().continueWithTask(task -> task.isFaulted() ? reconnect(board) : task);
    }

    // give a new instance of the sensor connection fragment
    public static Fragment newInstance() {
        return new SensorConnectionFragment();
    }



    // This is what lets you navigate back to the home page from this sensor connection activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);


                break;



            //how to destroy SensorConnectionActivity once we leave?

        }
    }

}




// old bluetooth connection code:

//package com.example.mhealth_build;
//
////import android.content.ComponentName;
//import android.app.Activity;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothManager;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.fragment.app.Fragment;
//
//import com.mbientlab.bletoolbox.scanner.BleScannerFragment;
//import com.mbientlab.metawear.MetaWearBoard;
//import com.mbientlab.metawear.android.BtleService;
//
//import java.util.HashMap;
//
//import bolts.Task;
//
//public class SensorConnectionFragment extends Fragment implements ServiceConnection {
//
//    private BtleService.LocalBinder serviceBinder;
//    //private Context context;
//
//    // private final HashMap<DeviceState, MetaWeaRBoard> stateToBoards;
//    // private ConnectedDeviceAdapter connnectedDevices = null;
//    // public SensorConnectionFragment() {stateToBoards = new HashMap<>()}
//    // private String s
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // From last year's -- why is this happening in onCreate and not onCreateView?
//        Activity owner = getActivity();
//        //bind bluetooth
//        owner.getApplicationContext().bindService(new Intent(owner, BtleService.class), this, Context.BIND_AUTO_CREATE);
//
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.connector_fragment, container, false);
////        Context context = getActivity();
////
////        // Bind the service when the activity is created
////        context.bindService(new Intent(context, BtleService.class),
////                this, Context.BIND_AUTO_CREATE);
//
//        return v;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        // Unbind the service when the activity is destroyed
//        context.unbindService(this);
//    }
//
//    @Override
//    public void onServiceConnected(ComponentName name, IBinder service) {
//        // Typecast the binder to the service's LocalBinder class
//        serviceBinder = (BtleService.LocalBinder) service;
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName componentName) { }
//
//
//    public static Task<Void> reconnect(final MetaWearBoard board) {
//        return board.connectAsync().continueWithTask(task -> task.isFaulted() ? reconnect(board) : task);
//    }
//
//    // Initialize bluetooth scan variable
//    public static final int REQUEST_START_BLE_SCAN = 1;
//
//    // Initialize metawear board object
//    private MetaWearBoard newBoard;
//
//    public void retrieveBoard() {
//        final BluetoothManager btManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
//        final BluetoothDevice remoteDevice = btManager.getAdapter().getRemoteDevice();
//    }
//
//}
//
//// Now do I need this to be a fragment triggered from a button press on screen one?
//// Or do I need it to run in the background, potentially in any fragment?
