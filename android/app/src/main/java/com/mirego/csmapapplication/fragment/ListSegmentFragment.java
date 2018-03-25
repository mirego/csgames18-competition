package com.mirego.csmapapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mirego.csmapapplication.R;

public final class ListSegmentFragment extends Fragment {

    TextView name;
    TextView component;
    TextView lat_lon;
    TextView distance;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment

        View myFragmentView =  inflater.inflate(R.layout.fragment_list, container, false);

        name = myFragmentView.findViewById(R.id.name);
        component = myFragmentView.findViewById(R.id.component);
        lat_lon = myFragmentView.findViewById(R.id.lat_lon);
        distance = myFragmentView.findViewById(R.id.distance);
        return myFragmentView;
    }

//    private HashMap _$_findViewCache;
//
//    @Nullable
//    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
//        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
//        return inflater.inflate(2131427372, container, false);
//    }
//
//    public View _$_findCachedViewById(int var1) {
//        if(this._$_findViewCache == null) {
//            this._$_findViewCache = new HashMap();
//        }
//
//        View var2 = (View)this._$_findViewCache.get(Integer.valueOf(var1));
//        if(var2 == null) {
//            View var10000 = this.getView();
//            if(var10000 == null) {
//                return null;
//            }
//
//            var2 = var10000.findViewById(var1);
//            this._$_findViewCache.put(Integer.valueOf(var1), var2);
//        }
//
//        return var2;
//    }
//
//    public void _$_clearFindViewByIdCache() {
//        if(this._$_findViewCache != null) {
//            this._$_findViewCache.clear();
//        }
//
//    }

    // $FF: synthetic method
//    public void onDestroyView() {
//        super.onDestroyView();
//        this._$_clearFindViewByIdCache();
//    }
}
