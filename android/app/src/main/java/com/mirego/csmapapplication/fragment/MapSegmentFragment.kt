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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
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

        val url = "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json"

        var requestQueue = Volley.newRequestQueue(this.context);

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (i in 0 until response.length()) {

                        val piece = response.getJSONObject(i)
                        val name = piece.getString("name")
                        val type = piece.getString("type")
                        if (!piece.isNull("lat") && !piece.isNull("lon")) {
                            val lat = piece.getDouble("lat")
                            val lon = piece.getDouble("lon")
                            println(name)
                            var resourceId = context?.resources?.getIdentifier("ic_part_" + type, "drawable", context?.packageName)
                            resourceId?.let {
                                this.mapView.getMapAsync { map ->
                                    map.addMarker(
                                            MarkerOptions()
                                                    .position(LatLng(lat, lon))
                                                    .title(name)
                                                    .icon(createPinForPart(resourceId))
                                    )
                                }
                            }

                        }
                    }
                },
                Response.ErrorListener { error ->
                    println("Something here")
                    // TODO: Handle error
                }
        )

        requestQueue.add(jsonArrayRequest)
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
