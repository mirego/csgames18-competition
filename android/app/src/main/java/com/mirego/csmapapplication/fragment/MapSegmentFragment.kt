package com.mirego.csmapapplication.fragment

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.model.VesselPart
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*


class MapSegmentFragment : Fragment(), OnMapReadyCallback {

    private var vesselParts: List<VesselPart>? = emptyList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false).also { mapSegmentView ->
            mapSegmentView.mapView.onCreate(savedInstanceState)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        vesselParts?.forEach { vesselPart ->
            if (vesselPart.lat != null && vesselPart.lon != null) {
                val resourceId = context?.resources?.getIdentifier("ic_part_" + vesselPart.type, "drawable", context?.packageName)
                val latlng = LatLng(vesselPart.lat.toDouble(), vesselPart.lon.toDouble())
                mapView.getMapAsync { map ->
                    map.addMarker(
                            MarkerOptions()
                                    .position(latlng)
                                    .title(vesselPart.name + " - " + vesselPart.component)
                                    .icon(createPinForPart(resourceId!!))
                    )
                }
            }
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        // Nothing to do here
    }

    fun setVesselParts(vesselParts: List<VesselPart>?) {
        this.vesselParts = vesselParts
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
}