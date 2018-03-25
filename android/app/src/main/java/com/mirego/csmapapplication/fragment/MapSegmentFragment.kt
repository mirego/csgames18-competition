package com.mirego.csmapapplication.fragment

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.helper.DrawableHelper
import com.mirego.csmapapplication.model.Mapping
import com.mirego.csmapapplication.model.PointClusterItem
import com.mirego.csmapapplication.rendering.ClusterRenderer
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*


class MapSegmentFragment : Fragment(), OnMapReadyCallback {
    var clusterManager: ClusterManager<PointClusterItem>? = null

    var mappings = arrayListOf<Mapping>()
    var currentLocation = LatLng(0.0, 0.0)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            mappings = it.getParcelableArrayList<Mapping>("mappings")
            currentLocation = it.getParcelable<LatLng>("location")
        }

        return inflater.inflate(R.layout.fragment_map, container, false).also { mapSegmentView ->
            mapSegmentView.mapView.onCreate(savedInstanceState)
            mapSegmentView.mapView.getMapAsync { map ->
                clusterManager = ClusterManager(activity, map)
                clusterManager?.let {
                    it.renderer = ClusterRenderer(context!!, map, it)
                    it.onCameraIdle()
                    map.setOnMarkerClickListener(clusterManager)
                }

                map.moveCamera(CameraUpdateFactory.newCameraPosition(
                        CameraPosition.Builder()
                                .target(currentLocation)
                                .zoom(11.0f)
                                .build()
                ))
                mappings.forEach {
                    clusterManager?.addItem(PointClusterItem(it))
                    if (it.lat != null && it.lon != null) {
                        map.addMarker(
                                MarkerOptions()
                                        .position(LatLng(it.lat, it.lon))
                                        .title(it.name)
                                        .icon(DrawableHelper.createPinForPart(DrawableHelper.getDrawableByType(it.type), resources))
                        )
                    }
                }
                clusterManager?.cluster()
                if (currentLocation.latitude != 0.0 && currentLocation.longitude != 0.0) {
                    map.addMarker(
                            MarkerOptions()
                                    .position(currentLocation)
                                    .title("My location")
                                    .icon(DrawableHelper.createPinForPart(R.drawable.ic_pin_cluster, resources))
                    )
                }
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