package com.mirego.csmapapplication.fragment

import android.content.Context
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
import android.os.AsyncTask
import android.support.annotation.DrawableRes
import android.util.Log
import com.mirego.csmapapplication.ObjectSerializer
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private val latList = ArrayList<String>()
private val lonList = ArrayList<String>()

class MapSegmentFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false).also { mapSegmentView ->
            mapSegmentView.mapView.onCreate(savedInstanceState)
            mapSegmentView.mapView.getMapAsync { map ->
                apiCall()
                for (i: Int in 0..latList.size) {
                    map.addMarker(
                            MarkerOptions()
                                    .position(LatLng(java.lang.Double.parseDouble(latList.get(i)),java.lang.Double.parseDouble(lonList.get(i))))
                                    .title("Test Opin")
                                    .icon(createPinForPart(R.drawable.ic_part_bulb))
                    )
                }
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

    private fun apiCall() {
        val downloadTask = DownloadTask()
        var result: String? = null
        try {
            result = downloadTask.execute("https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json").get()
            val received = JSONArray(result)
            for(entry: Int in 0..received.length()-1) {
                Log.i("Response", received.get(entry).toString())
                var entrydata = JSONObject(received.get(entry).toString())
                var lat = entrydata.get("lat").toString()
                var lon = entrydata.get("lon").toString()

                latList.add(lat)
                lonList.add(lon)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class DownloadTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg urls: String): String {
            var result = ""
            val url: URL
            val urlConnection: HttpURLConnection
            try {
                url = URL(urls[0])
                urlConnection = url.openConnection() as HttpURLConnection
                val `in` = urlConnection.inputStream
                val reader = InputStreamReader(`in`)
                var data = reader.read()
                while (data != -1) {
                    val current = data.toChar()
                    result += current
                    data = reader.read()
                }
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                return "Failed"
            }
        }
    }
}