package com.mirego.csmapapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mirego.csmapapplication.ExtendedMapPingApplication;
import com.mirego.csmapapplication.adapters.LocationAdapter;
import com.mirego.csmapapplication.R;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by jean-luc on 18-03-24.
 */

public class ExtendedListSegmentFragment extends ListSegmentFragment {
    private LocationAdapter mLocationAdapter;
    private ListView mListView;

    @Override
    public void onCreate(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = super.onCreateView(inflater, container, savedInstanceState);

        mLocationAdapter = new LocationAdapter(this.getContext(), ExtendedMapPingApplication.getInstance().getRepository().getLocations());
        mListView = v.findViewById(R.id.listViewLocations);
        mListView.setAdapter(mLocationAdapter);

        return v;
    }
}
