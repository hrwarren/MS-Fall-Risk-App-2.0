package com.example.mhealth_build;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class DeviceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView deviceAddress, connectingText, xVal, yVal, zVal, sensorLoco;
    ProgressBar connectingProgress;
    DeviceState mDevice;
    Context mContext;

    public DeviceHolder(LayoutInflater inflater, ViewGroup parent) {
        // inflate the layout for the list of sensors that the phone sees
        super(inflater.inflate(R.layout.sensor_list, parent, false));
        itemView.setOnClickListener(this);

        // declare the text views for device address, connecting text, x, y, and z accel values,
        // ...sensor location, and progress bar
        deviceAddress = (TextView) itemView.findViewById(R.id.status_mac_address);
        connectingText = (TextView) itemView.findViewById(R.id.text_connecting);
        xVal = (TextView) itemView.findViewById(R.id.xVal);
        yVal = (TextView) itemView.findViewById(R.id.yVal);
        zVal = (TextView) itemView.findViewById(R.id.zVal);
        sensorLoco = itemView.findViewById(R.id.sensorLoco);
        connectingProgress = (ProgressBar) itemView.findViewById(R.id.connecting_progress);
    }

    public void bind(DeviceState device) {
        mDevice = device;
        // set displayed x,y,z coordinate values to those obtained by the sensor
        xVal.setText(mDevice.getX());
        yVal.setText(mDevice.getY());
        zVal.setText(mDevice.getZ());
        Log.i("JDH", "bind(): " + mDevice.getX() + " " + mDevice.getY() + " " + mDevice.getZ());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(mContext, "Connected", Toast.LENGTH_LONG).show();
    }

    public void setContext(Context context) {
        mContext = context;
    }
}

