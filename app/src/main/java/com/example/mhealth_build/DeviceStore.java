package com.example.mhealth_build;

import android.bluetooth.BluetoothDevice;
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
        mDevices = new ArrayList<>();
        for (int i=0; i<4; ++i) {
            DeviceState device = new DeviceState("device " + i);
//            device.setTitle("Crime #" + i);
//            dev.setSolved(i%2 == 0);
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
