package com.example.mhealth_build.sensorConnection;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DeviceStore {
    private static DeviceStore sDeviceStore;
    private List<DeviceState> mDevices;

    public static DeviceStore get(Context context) {
        if (sDeviceStore == null)
            sDeviceStore = new DeviceStore(context);
        return sDeviceStore;
    }

    private DeviceStore(Context context) {
        // adding bluetooth devices to the array that gets displayed in the sensor connection screen
        mDevices = new ArrayList<>();
        for (int i=0; i<4; ++i) {
            DeviceState device = new DeviceState("device " + i);
            mDevices.add(device);
        }
    }

    public List<DeviceState> getDevices() {
        return mDevices;
    }

    public DeviceState getDevice(String name) {
        for (DeviceState device : mDevices) {
            if (device.getName().equals(name))
                return device;
        }
        return null;
    }
}
