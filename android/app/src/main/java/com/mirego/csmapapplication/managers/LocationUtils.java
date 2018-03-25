package com.mirego.csmapapplication.managers;

import android.location.Location;
import android.location.LocationListener;

/**
 * Created by jean-luc on 18-03-24.
 */

public class LocationUtils {

    Location deviceLocation;

    public Location getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(Location deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public float distanceFromDevice(Location location)
    {
        if(this.deviceLocation == null)
            return 0.0f;
        return this.deviceLocation.distanceTo(location);
    }

}
