package com.mirego.csmapapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
    private Spinner mSpinner;

    @Override
    public void onCreate(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = super.onCreateView(inflater, container, savedInstanceState);

        mLocationAdapter = new LocationAdapter(this.getContext(), ExtendedMapPingApplication.getInstance().getRepository());
        mSpinner = v.findViewById(R.id.spinnerFilterTypes);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, ExtendedMapPingApplication.getInstance().getRepository().getTypes());
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               ExtendedMapPingApplication.getInstance().getRepository().filterByType(adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ExtendedMapPingApplication.getInstance().getRepository().unfilter();
            }
        });
        mListView = v.findViewById(R.id.listViewLocations);
        mListView.setAdapter(mLocationAdapter);

        return v;
    }
}
