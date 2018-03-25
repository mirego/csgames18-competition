package com.mirego.csmapapplication.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.mirego.csmapapplication.ExtendedMapPingApplication;
import com.mirego.csmapapplication.MapPingApplication;

/**
 * Created by jean-luc on 18-03-24.
 */

public class ExtendedMainActivity extends MainActivity {
    LocationManager locationManager;
    LocationListenerImplementation locationListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

        ((MapPingApplication)getApplication()).getNetComponent().inject(this);


        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListenerImplementation();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                )
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

        }
        super.onCreate(savedInstanceState, persistentState);


    }

    private class LocationListenerImplementation implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location) {
            ExtendedMapPingApplication.getInstance().getLocationUtility().setDeviceLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }
    }
}
