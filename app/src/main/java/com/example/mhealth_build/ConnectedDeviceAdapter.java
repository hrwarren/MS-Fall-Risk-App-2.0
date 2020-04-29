package com.example.mhealth_build;
// Array adapter hold information about each device connection as well as displaying it on the screen

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ConnectedDeviceAdapter extends RecyclerView.Adapter<DeviceHolder> {

    //Store member variable for attached devices
    private List<DeviceState> mConnectedDevices;
    Context mContext;

/*-------------------- jsh ---------------->
    public class DeviceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView deviceAddress, connectingText, xVal, yVal, zVal, sensorLoco;
        ProgressBar connectingProgress;
        DeviceState mDevice;

        public DeviceHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.sensor_list, parent, false));
            itemView.setOnClickListener(this);
            deviceAddress = (TextView) itemView.findViewById(R.id.status_mac_address);
            connectingText = (TextView) itemView.findViewById(R.id.text_connecting);
            xVal = (TextView) itemView.findViewById(R.id.xVal);
            yVal = (TextView) itemView.findViewById(R.id.yVal);
            zVal = (TextView) itemView.findViewById(R.id.zVal);
            //sensorLoco = (TextView) ConnectedDeviceAdapter.this.getItemViewType().findViewById(R.id.sensorLoco);
            sensorLoco = itemView.findViewById(R.id.sensorLoco);
            connectingProgress = (ProgressBar) itemView.findViewById(R.id.connecting_progress);
        }

        public void bind(DeviceState device) {
            mDevice = device;
            xVal.setText(mDevice.getX());
            yVal.setText(mDevice.getY());
            zVal.setText(mDevice.getZ());
            Log.i("JDH", "bind(): " + mDevice.getX() + " " + mDevice.getY() + " " + mDevice.getZ());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "Hello", Toast.LENGTH_LONG).show();
        }
    }
------------------- jdh -------------------*/

    public void setContext(Context context) {
        mContext = context;
    }

    public ConnectedDeviceAdapter(List<DeviceState> connectedDevices) {
        mConnectedDevices = connectedDevices;
    }

    @Override
    public DeviceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // provide context for the adapter
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate layout for device visual
        DeviceHolder viewHolder = new DeviceHolder(inflater, parent);

        // return new holder instance
        viewHolder.setContext(mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DeviceHolder viewHolder, int position) {

//        The viewholders for displaying x, y, and z accel values, currently commented out
//        viewHolder = new ViewHolder(convertView);
//        viewHolder.deviceAddress = convertView.findViewById(R.id.status_mac_address);
//        viewHolder.sensorLoco = convertView.findViewById(R.id.sensorLoco);
//        viewHolder.xVal = convertView.findViewById(R.id.xVal);
//        viewHolder.yVal = convertView.findViewById(R.id.yVal);
//        viewHolder.zVal = convertView.findViewById(R.id.zVal);
//        viewHolder.connectingText = convertView.findViewById(R.id.text_connecting);
//        viewHolder.connectingProgress = convertView.findViewById(R.id.connecting_progress);
//
        Log.i("JDH", "onBindViewHolder, position = " + position);

        // determine the position in the list of each device
        DeviceState state = mConnectedDevices.get(position);
        viewHolder.bind(state);

/*------------------ jdh ----------->

        // Set position name based on order of connection, will be automated in a future version using calibration process
        if (position <= 0) {
            viewHolder.sensorLoco.setText("Medial Chest");
        } else {
            viewHolder.sensorLoco.setText("Anterior Thigh");
        }

        // set the last two characters of the device address to text view
        viewHolder.deviceAddress.setText(state.btDevice.getAddress().substring(15, 17));

        // holds state of connection for progress bar, once connected shows x, y, z components of accel
        if (state.connecting) {
            viewHolder.connectingProgress.setVisibility(View.VISIBLE);
            viewHolder.connectingText.setVisibility(View.VISIBLE);
        } else {
            viewHolder.xVal.setVisibility(View.VISIBLE);
            viewHolder.yVal.setVisibility(View.VISIBLE);
            viewHolder.zVal.setVisibility(View.VISIBLE);
            if (state.xVal != null) {
                viewHolder.xVal.setText(state.xVal);
                viewHolder.yVal.setText(state.yVal);
                viewHolder.zVal.setText(state.zVal);
            }
            viewHolder.connectingProgress.setVisibility(View.GONE);
            viewHolder.connectingText.setVisibility(View.GONE);
        }
------------------------- jdh ----------------------*/
    }

    @Override
    public int getItemCount() {
        int count = mConnectedDevices.size();
        Log.i("JDH", "getItemCount() = " + count);
        return count;
    }

}