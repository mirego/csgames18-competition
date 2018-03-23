package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.mirego.csmapapplication.R
import kotlinx.android.synthetic.main.fragment_map.*

class MapSegmentFragment : Fragment(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapview.onCreate(savedInstanceState)
        mapview.onResume()
        mapview.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()
    }

}