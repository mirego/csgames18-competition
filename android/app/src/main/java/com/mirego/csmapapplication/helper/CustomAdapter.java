package com.mirego.csmapapplication.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.JsonParser;
import com.mirego.csmapapplication.R;
import com.mirego.csmapapplication.model.Piece;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by franc on 3/24/2018.
 */

public class CustomAdapter extends ArrayAdapter<Piece> implements View.OnClickListener{

    private ArrayList<Piece> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtDesc;
        TextView txtLocation;
        TextView txtDistance;
        ImageView image;
    }

    public CustomAdapter(ArrayList<Piece> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;
        JSONObject fds;
        JSONTokener fdsf;
        JSONArray sfdsfs;
    }

    @Override
    public void onClick(View v) {

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Piece Piece = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtNom);
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.txtDesc);
            viewHolder.txtLocation = (TextView) convertView.findViewById(R.id.txtLocation);
            viewHolder.txtDistance = (TextView) convertView.findViewById(R.id.txtDistance);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageIcon);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.txtName.setText(Piece.name);
        viewHolder.txtDesc.setText(Piece.component);
        viewHolder.txtLocation.setText(Piece.lon + ", " + Piece.lat);
        viewHolder.txtDistance.setText("Distance");
        //viewHolder.image.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
