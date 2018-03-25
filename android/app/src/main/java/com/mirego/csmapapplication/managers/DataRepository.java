package com.mirego.csmapapplication.managers;


import com.google.gson.Gson;
import com.mirego.csmapapplication.model.IDataReceiver;
import com.mirego.csmapapplication.model.SpaceshipComponent;
import com.mirego.csmapapplication.module.DataModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 * Created by jean-luc on 18-03-24.
 */

public class DataRepository {
    private List<SpaceshipComponent> spaceshipComponents;
    private String filterType = "";
    private List<SpaceshipComponent> filteredSpaceshipComponents;
    private IDataReceiver receiver;

    private final DataModule dataModule;
    private final String repositoryUrl = "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json";
    public DataRepository()
    {
        dataModule = new DataModule();
        spaceshipComponents = new ArrayList<SpaceshipComponent>();
        filterType = "";
    }

    public void attachDataReceiver(IDataReceiver receiver)
    {
        this.receiver = receiver;
    }

    public void load()
    {
        String jsonPayload = dataModule.readFrom(repositoryUrl);
        Gson gson = new Gson();
        SpaceshipComponent[] locationsArray = gson.fromJson(jsonPayload, SpaceshipComponent[].class);
        if(locationsArray == null)
            return; 
        spaceshipComponents = Arrays.asList(locationsArray);
    }

    public List<SpaceshipComponent> getSpaceshipComponents() {
        List<SpaceshipComponent> target = spaceshipComponents;
        if(filterType.length() > 0 )
            target = filteredSpaceshipComponents;
        return target;
    }

    public SpaceshipComponent getLocationAt(int index)
    {
        List<SpaceshipComponent> target = spaceshipComponents;
        if(filterType.length() > 0 )
            target = filteredSpaceshipComponents;
        if(index < 0 || index >= target.size())
            return null;
        return target.get(index);
    }

    public List<String> getTypes()
    {
        ArrayList<String> types = new ArrayList<String>();
        types.add("");
        for(SpaceshipComponent c: this.spaceshipComponents)
            if(!types.contains(c.type))
                types.add(c.type);
        return types;
    }
    public void filterByType(String type)
    {
        if(type == null || type.length() == 0) {
            this.unfilter();
            return;
        }
        this.filterType = type;
        List<SpaceshipComponent> comps = new ArrayList<>();
        for(SpaceshipComponent c: this.spaceshipComponents)
            if(c.type.equals(filterType))
                comps.add(c);
        filteredSpaceshipComponents = comps;
        this.notifyReceiver();
    }

    public void unfilter()
    {
        this.filterType = "";
        this.notifyReceiver();
    }

    private void notifyReceiver()
    {
        if(this.receiver != null)
            this.receiver.receive();
    }



}
