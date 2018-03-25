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
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mirego.csmapapplication.R
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*

class MapSegmentFragment : Fragment(), OnMapReadyCallback {

    private var lon: Double = 46.7794201
    private var lat: Double = -71.2778703
    private var title: String = "Placeholder"
    private var type: String = "bulb"

    private var globalMapSegmentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false).also {
            mapSegmentView ->
            globalMapSegmentView = mapSegmentView
            mapSegmentView.mapView.onCreate(savedInstanceState)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onMapReady(p0: GoogleMap?) {
        // Nothing to do here
    }

    private fun createPinForPart(@DrawableRes partResId: Int): BitmapDescriptor {
        val pinDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_pin, null)
        val partDrawable = ResourcesCompat.getDrawable(resources, partResId, null)!!

        val bitmap = Bitmap.createBitmap(pinDrawable!!.intrinsicWidth,
                pinDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        pinDrawable.setBounds(0, 0, canvas.width, canvas.height)
        pinDrawable.draw(canvas)

        val partMargin = canvas.width / 4
        val partSize = canvas.width - partMargin
        partDrawable.setBounds(partMargin, partMargin, partSize, partSize)
        partDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    fun setPinLocation(lon: Double, lat: Double, title: String, type: String){
        this.lon = lon
        this.lat = lat
        this.title = title
        this.type = type

        val uri = "@drawable/ic_part_" + type
        val imageResource = activity?.getResources()?.getIdentifier(uri, null, activity?.getPackageName())

        globalMapSegmentView?.mapView?.getMapAsync { map ->
            map.clear()
            map.addMarker(
                MarkerOptions()
                        .position(LatLng(lat, lon))
                        .title(title)
                        .icon(createPinForPart(imageResource?:1))
            )
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lon), 16f))
        }
    }
}