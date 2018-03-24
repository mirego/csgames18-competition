package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.mirego.csmapapplication.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.mapView
import kotlinx.android.synthetic.main.fragment_map.view.mapView

class MapSegmentFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false).also { mapSegmentView ->
            mapSegmentView.mapView.onCreate(savedInstanceState)
            mapSegmentView.mapView.getMapAsync { map ->
                val miregoMtlCoordinates = LatLng(45.4920403, -73.5584612)

                map.addMarker(
                    MarkerOptions()
                        .position(miregoMtlCoordinates)
                        .title("Mirego Mtl")
//                        .icon(
//                            BitmapDescriptorFactory.fromResource(R.drawable.ic_cluster)
//                        )
                )

                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        miregoMtlCoordinates,
                        14.0f
                    )
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onMapReady(p0: GoogleMap?) {
        // Nothing to do here

    }
}