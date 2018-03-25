package com.mirego.csmapapplication.model;

/**
 * Created by Pourliver on 2018-03-24.
 */

public class PartItem {
    private String name;
    private String component;
    private String notes;
    private String type;
    private String lat;
    private String lon;
    private String address;

    public PartItem(String _name, String _component, String _notes, String _type, String _lat, String _lon, String _address) {
        this.name = _name;
        this.component = _component;
        this.notes = _notes;
        this.type = _type;
        this.lat = _lat;
        this.lon = _lon;
        this.address = _address;
    }

    public static PartItem PartItemGenerator() {
        return new PartItem("Test name", "Space Machine", "For debugging purposes", "type", "0", "0", "no address");
    }

    public static PartItem[] generateDebugData(int number) {
        if (number < 0) throw new Error("Cannot generate negative amount of PartItems");
        PartItem[] itemList = new PartItem[number];

        for (int i = 0; i < number; i++) {
            itemList[i] = PartItem.PartItemGenerator();
        }

        return itemList;
    }
}
