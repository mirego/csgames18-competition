package com.mirego.csmapapplication.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toolbar;
import com.mirego.csmapapplication.MapPingApplication;
import com.mirego.csmapapplication.R;
import com.mirego.csmapapplication.R.id;
import com.mirego.csmapapplication.fragment.ListSegmentFragment;
import com.mirego.csmapapplication.fragment.MapSegmentFragment;
import com.mirego.csmapapplication.service.GitHubService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.widget.TextView;
import android.view.LayoutInflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<HashMap<String, String>> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mCardView;
        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<HashMap<String, String>> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        TextView name = holder.mCardView.findViewById(R.id.name);
        TextView component = holder.mCardView.findViewById(R.id.component);
        TextView lat_lon = holder.mCardView.findViewById(R.id.lat_lon);
        TextView distance = holder.mCardView.findViewById(R.id.distance);
        name.setText(mDataset.get(position).get("name"));
        component.setText(mDataset.get(position).get("component"));
        lat_lon.setText(mDataset.get(position).get("lat")+"  "+mDataset.get(position).get("lon"));
        distance.setText(mDataset.get(position).get("address"));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}