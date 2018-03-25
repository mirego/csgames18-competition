package com.mirego.csmapapplication.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mirego.csmapapplication.ExtraKeys;
import com.mirego.csmapapplication.MapPingApplication;
import com.mirego.csmapapplication.R;
import com.mirego.csmapapplication.activity.PieceDetailActivity;
import com.mirego.csmapapplication.model.Piece;

public class MapSegmentFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, LocationListener {

    private final int LOCATION_PERMISSION = 3;

    MapView mapView;

    GoogleMap mMap;

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    private OnFragmentInteractionListener mListener;
    private ProgressDialog progressDialog;
    private LocationManager locationManager;

    public MapSegmentFragment() {
        // Required empty public constructor
    }

    public static MapSegmentFragment newInstance(String param1) {
        MapSegmentFragment fragment = new MapSegmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;
    }

    public void loadPieces() {

        for(int i = 0; i < MapPingApplication.pieces.size(); i++) {
            Piece piece = MapPingApplication.pieces.get(i);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(piece.getLatitude(), piece.getLongitude()))
                    .title(piece.getName())
                    .icon(BitmapDescriptorFactory.fromBitmap(getColoredMarkerBitmap())))
                    .setTag(i);
        }

    }


    private Bitmap getColoredMarkerBitmap() {
        Bitmap ob = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_pin);
        Bitmap obm = Bitmap.createScaledBitmap(ob, ob.getWidth() + (ob.getWidth() * 30 / 100), ob.getHeight() + (ob.getHeight() * 30 / 100), false);
        Canvas canvas = new Canvas(obm);

        Paint paint = new Paint();
        //paint.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(getActivity(), colorId), PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(obm, 0f, 0f, paint);
        return obm;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                    mMap.setOnMarkerClickListener(this);
                    mMap.getUiSettings().setMyLocationButtonEnabled(false);
                    mMap.getUiSettings().setMapToolbarEnabled(false);

                } else {
                    //TODO
                }
                return;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, LOCATION_PERMISSION);
        } else {
            locationManager = (LocationManager)getActivity().getSystemService(getActivity().LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, true);

            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(bestProvider, 2000, 0, this);

            mMap.setMyLocationEnabled(true);
            mMap.setOnMarkerClickListener(this);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.getUiSettings().setMapToolbarEnabled(false);
        }
        loadPieces();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {


        /*
        builder.setTitle("Choose an option")
                .setItems(R.array.piece_menu, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0) {
                            int index = (int)marker.getTag();
                            Intent intent = new Intent(getActivity(), PieceDetailActivity.class);
                            intent.putExtra(ExtraKeys.PIECE_INDEX, index);
                            startActivity(intent);
                        }  else {

                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();*/

        int index = (int)marker.getTag();
        Intent intent = new Intent(getActivity(), PieceDetailActivity.class);
        intent.putExtra(ExtraKeys.PIECE_INDEX, index);
        startActivity(intent);
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
