package com.mirego.csmapapplication.repository;


import com.google.gson.Gson;
import com.mirego.csmapapplication.model.Location;
import com.mirego.csmapapplication.module.DataModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by jean-luc on 18-03-24.
 */

public class DataRepository {
    private List<Location> locations;

    private final DataModule dataModule;
    private final String repositoryUrl = "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json";
    public DataRepository()
    {
        dataModule = new DataModule();
        locations = new ArrayList<Location>();
    }

    public void load()
    {
        String jsonPayload = dataModule.readFrom(repositoryUrl);
        Gson gson = new Gson();
        Location[] locationsArray = gson.fromJson(jsonPayload, Location[].class);
        locations = Arrays.asList(locationsArray);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Location getLocationAt(int index)
    {
        if(index < 0 || index >= locations.size())
            return null;
        return locations.get(index);
    }

}
