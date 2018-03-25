package com.mirego.csmapapplication.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mirego.csmapapplication.ExtendedMapPingApplication;
import com.mirego.csmapapplication.R;

import com.mirego.csmapapplication.managers.DataRepository;
import com.mirego.csmapapplication.managers.LocationUtils;
import com.mirego.csmapapplication.model.IDataReceiver;
import com.mirego.csmapapplication.model.SpaceshipComponent;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by jean-luc on 18-03-24.
 */

public class LocationAdapter extends ArrayAdapter<SpaceshipComponent> implements IDataReceiver{
    private Context mContext;
    private LayoutInflater mInflater = null;

    private List<SpaceshipComponent> locations;
    DataRepository repository;


    public LocationAdapter(@NonNull Context context, DataRepository repository) {
        super(context, R.layout.location_listitem, repository.getSpaceshipComponents());
        this.repository = repository;
        repository.attachDataReceiver(this);
        mContext = context;
        locations = repository.getSpaceshipComponents();
    }

    public void receive() {
        this.populate(this.repository.getSpaceshipComponents());
    }

    static class ViewHolder {
        public TextView name;
        public TextView component;
        public TextView coordinates;
        public TextView distance;
        public ImageView icon;
        public Drawable drawable;
    }

    public void populate(List<SpaceshipComponent> comps) {
        this.locations = comps;
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
            holder.icon = (ImageView) rowView.findViewById(R.id.locationItemImage);

            rowView.setTag(holder);
        }
        else
        {
            rowView = convertView;
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        SpaceshipComponent r = (SpaceshipComponent)getItem(position);

        holder.name.setText(r.name);
        holder.component.setText(r.component);
        DecimalFormat format = new DecimalFormat("##.###");
        holder.coordinates.setText(String.format("%s; %s", format.format(r.lat), format.format(r.lon)));
        holder.distance.setText(""); //new DecimalFormat("##.#").format(ExtendedMapPingApplication.getInstance().getLocationUtility().distanceFromDevice(r.getLocation())));
        Context context = holder.icon.getContext();
        int id = context.getResources().getIdentifier(String.format("ic_part_%s", r.type), "drawable", context.getPackageName());
        Drawable d = context.getDrawable(id);
        if(d != null)
            holder.icon.setImageDrawable(d);
        else
            holder.icon.setImageResource(0);

        return rowView;
    }


}
