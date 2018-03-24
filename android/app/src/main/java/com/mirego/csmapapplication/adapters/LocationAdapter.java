package com.mirego.csmapapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mirego.csmapapplication.R;

import com.mirego.csmapapplication.model.Location;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jean-luc on 18-03-24.
 */

public class LocationAdapter extends ArrayAdapter<Location> {
    private Context mContext;
    private LayoutInflater mInflater = null;

    private List<Location> locations;


    public LocationAdapter(@NonNull Context context, List<Location> objects) {
        super(context, R.layout.location_listitem, objects);
        mContext = context;
        locations = objects;
    }

    static class ViewHolder {
        public TextView name;
        public TextView component;
        public TextView coordinates;
        public TextView distance;
    }

    public void populate(List<Location> routines) {
        this.locations = routines;
        notifyDataSetChanged();
    }

    private LayoutInflater getInflater(){
        if(mInflater == null)
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return mInflater;
    }

    @Override
    public int getCount() {
        return this.locations.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView;

        if(convertView == null)
        {
            rowView = getInflater().inflate(R.layout.location_listitem, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) rowView.findViewById(R.id.locationItemName);
            holder.component = (TextView) rowView.findViewById(R.id.locationItemComponent);
            holder.coordinates = (TextView) rowView.findViewById(R.id.locationItemCoordinates);
            holder.distance = (TextView) rowView.findViewById(R.id.locationItemDistance);
            rowView.setTag(holder);
        }
        else
        {
            rowView = convertView;
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        Location r = (Location)getItem(position);

        holder.name.setText(r.name);
        holder.component.setText(r.component);
        DecimalFormat format = new DecimalFormat("##.###");
        holder.coordinates.setText(String.format("%s, %s", format.format(r.lat), format.format(r.lon)));
        holder.distance.setText("0");

        return rowView;
    }


}
