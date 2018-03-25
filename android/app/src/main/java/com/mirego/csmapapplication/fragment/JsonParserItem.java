package com.mirego.csmapapplication.fragment;

import com.mirego.csmapapplication.helper.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PainDeViande on 24/03/2018.
 */

public class JsonParserItem {
    String in;
    JSONObject reader = new JSONObject(in);

    ArrayList<Item> items = new ArrayList<Item>();

    public JsonParserItem() throws JSONException
    {
        JSONArray arr = new JSONArray("mapping");
        JSONObject sys = arr.getJSONObject(0);

        String name = sys.getString("name");
        String component = sys.getString("component");
        String notes = sys.getString("notes");
        String type = sys.getString("type");
        String address = sys.getString("address");
        float lat = Float.parseFloat(sys.getString("lat"));
        float lon = Float.parseFloat(sys.getString("lon"));

        System.out.println(name);

        Item item = new Item(name,component,notes,type,address,lat,lon);
        items.add(item);
    }

    public ArrayList<Item> getItemList()
    {
        return items;
    }
}

