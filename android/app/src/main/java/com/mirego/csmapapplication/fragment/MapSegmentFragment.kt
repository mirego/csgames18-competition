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



class MapSegmentFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false).also { mapSegmentView ->
            mapSegmentView.mapView.onCreate(savedInstanceState)
            mapSegmentView.mapView.getMapAsync { map ->
                map.addMarker(
                    MarkerOptions()
                        .position(LatLng(46.7794201,-71.2778703))
                        .title("Test Opin")
                        .icon(createPinForPart(R.drawable.ic_part_bulb))
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