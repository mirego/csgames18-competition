package com.mirego.csmapapplication;

import android.os.StrictMode;

import com.mirego.csmapapplication.repository.DataRepository;

/**
 * Created by jean-luc on 18-03-24.
 */

public class ExtendedMapPingApplication extends MapPingApplication {

    private static ExtendedMapPingApplication instance;
    public static ExtendedMapPingApplication getInstance()
    {
        return instance;
    }
    private DataRepository dataRepository;

    public DataRepository getRepository() {
        return dataRepository;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        dataRepository = new DataRepository();dataRepository.load();


    }


}
