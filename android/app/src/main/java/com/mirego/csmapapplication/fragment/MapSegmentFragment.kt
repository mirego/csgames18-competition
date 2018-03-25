package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.mirego.csmapapplication.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.mapView
import kotlinx.android.synthetic.main.fragment_map.view.mapView
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Canvas
import android.graphics.Bitmap
import android.support.v4.content.res.ResourcesCompat
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.util.Log
import com.mirego.csmapapplication.model.VesselPart


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
                var resourceId = context?.resources?.getIdentifier("ic_part_" + vesselPart.type, "drawable", context?.packageName)
                var latlng = LatLng(vesselPart.lat.toDouble(), vesselPart.lon.toDouble())
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
        Log.i("MagSegmentFragment", "Vessel part size:" + this.vesselParts?.size)
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