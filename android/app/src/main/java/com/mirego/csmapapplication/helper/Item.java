package com.mirego.csmapapplication.helper;

/**
 * Created by PainDeViande on 24/03/2018.
 */

public class Item {
    private String name, component, notes, type, address;
    private float lat, lon;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public String getComponent() {
        return component;
    }

    public String getNotes() {
        return notes;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public Item(String name, String component, String notes, String type, String address, float lat, float lon) {
        this.name = name;
        this.component = component;
        this.notes = notes;
        this.type = type;
        this.address = address;
        this.lat = lat;
        this.lon = lon;

    }

}
