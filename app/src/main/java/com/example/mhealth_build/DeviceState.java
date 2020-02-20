package com.example.mhealth_build;

import android.bluetooth.BluetoothDevice;

public class DeviceState {
    final BluetoothDevice btDevice;
    String xVal;
    String yVal;
    String zVal;
    boolean connecting;

    DeviceState(BluetoothDevice btDevice) {
        this.btDevice= btDevice;
        this.xVal = null;
        this.yVal = null;
        this.zVal = null;
    }


    @Override
    public boolean equals(Object obj) {

        return (obj == this) ||
                ((obj instanceof DeviceState) && btDevice.equals(((DeviceState) obj).btDevice));
    }
}

