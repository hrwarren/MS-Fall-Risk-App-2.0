package com.example.mhealth_build;

import android.bluetooth.BluetoothDevice;

public class DeviceState {
    final BluetoothDevice btDevice;
    String xVal;
    String yVal;
    String zVal;
    boolean connecting;
    //jdh added this, as a temporary thing
    String name;

    DeviceState(BluetoothDevice btDevice) {
        this.btDevice= btDevice;
        this.xVal = null;
        this.yVal = null;
        this.zVal = null;
    }

    //jdh added this as a temporary thing
    DeviceState(String name) {
        this.btDevice = null;
        this.xVal = "x";
        this.yVal = "y";
        this.zVal = "z";
        this.connecting = false;
        this.name = name;
    }

    public String getX() {
        return this.xVal;
    }

    public String getY() {
        return this.yVal;
    }

    public String getZ() {
        return this.zVal;
    }

    //jdh added this as temporary thing
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {

        return (obj == this) ||
                ((obj instanceof DeviceState) && btDevice.equals(((DeviceState) obj).btDevice));
    }
}
