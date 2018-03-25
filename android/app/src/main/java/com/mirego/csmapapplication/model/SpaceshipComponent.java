package com.mirego.csmapapplication.model;

import android.location.Location;

/**
 * Created by leanne on 24/03/18.
 */

public class SpaceshipComponent {
    public String name;
    public String type;
    public String address;
    public String component;
    public String notes;
    public double lat;
    public double lon;

    private Location location;
    public Location getLocation()
    {
        if(location == null)
        {
            location = new Location("");
            location.setLatitude(lat);
            location.setLongitude(lon);
        }

        return location;
    }



    public SpaceshipComponent(){
        
    }
}
