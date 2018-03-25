package com.mirego.csmapapplication.activity


import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.mirego.csmapapplication.MapPingApplication
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.fragment.ListSegmentFragment
import com.mirego.csmapapplication.fragment.MapSegmentFragment
import com.mirego.csmapapplication.model.Part
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject
import com.mirego.csmapapplication.service.PartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


class MainActivity : FragmentActivity() {

    private val listFragment = ListSegmentFragment()
    private val mapFragment = MapSegmentFragment()
    private var selectedSegmentIndex = 0
    var currentLocation: Location by Delegates.observable(Location("")) { _, _, _ ->
        sortData()
    }
    private var listSpaceshipPart: List<Part>? = null

    private var locationManager : LocationManager? = null
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            currentLocation = location
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    private lateinit var segmentButtons: List<ImageButton>

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MapPingApplication).netComponent.inject(this)

        segmentButtons = listOf(listButton, mapButton, arButton)

        setActionBar(toolbar)

        if (savedInstanceState == null) {
            setupMainView()
        }

        setupButtons()

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        try {
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available")
        }
        downloadData()
    }

    private fun downloadData() {
        retrofit.create(PartService::class.java).listParts().enqueue(object : Callback<List<Part>> {
            override fun onFailure(call: Call<List<Part>>?, t: Throwable?) {
                Log.d("street's test", "Oops")
            }

            override fun onResponse(call: Call<List<Part>>?, response: Response<List<Part>>?) {
                listSpaceshipPart = response?.body()
                listFragment.onPartListLoad(listSpaceshipPart)
            }
        })
    }

    private fun sortData() {
        listSpaceshipPart =  listSpaceshipPart?.sortedWith(compareBy { calculateDistanceFromDevice(it) })
        listSpaceshipPart?.forEach { println(it.distance) }
    }

    private fun calculateDistanceFromDevice(part: Part): Float {
        val radius = 6378137.0f   // approximate Earth radius, *in meters*
        if (part.latitude == null || part.longitude == null) {
            return radius
        }

        val array = FloatArray(10)
        Location.distanceBetween(currentLocation.latitude, currentLocation.longitude, part.latitude, part.longitude, array)

        part.distance = array[0]
        return array[0]
    }

    private fun setupMainView() {
        supportFragmentManager.beginTransaction()
            .add(fragmentRoot.id, listFragment)
            .commit()
    }

    private fun onSegmentButtonClicked(button: ImageButton) {
        when (button) {
            listButton -> {
                replaceFragment(listFragment)
            }

            mapButton -> {
                replaceFragment(mapFragment)
            }

            arButton -> {
                startActivity(Intent(this, ArActivity::class.java))
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentRoot.id, fragment)
        transaction.commit()
    }

    private fun setupButtons() {
        val listener = View.OnClickListener { view: View ->
            for ((index, segmentButton) in segmentButtons.withIndex()) {
                if (segmentButton == view as ImageButton) {
                    selectedSegmentIndex = index
                    if (view != arButton) {
                        updateSegmentButtonsColor()
                    }
                    onSegmentButtonClicked(segmentButton)
                    break
                }
            }
        }

        for (button in segmentButtons) {
            button.setOnClickListener(listener)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SELECTED_SEGMENT_INDEX_KEY, selectedSegmentIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getInt(SELECTED_SEGMENT_INDEX_KEY)?.let {
            selectedSegmentIndex = it
            updateSegmentButtonsColor()
        }
    }

    private fun updateSegmentButtonsColor() {
        for ((index, segmentButton) in segmentButtons.withIndex()) {
            if (index == selectedSegmentIndex) {
                tintSegmentButton(segmentButton, true)
            } else {
                tintSegmentButton(segmentButton, false)
            }
        }
    }

    private fun tintSegmentButton(button: ImageButton, selected: Boolean) {
        button.setColorFilter(
            ContextCompat.getColor(
                this,
                if (selected) R.color.brightSunYellow else R.color.cloudGray
            )
        )
    }

    companion object {
        private const val SELECTED_SEGMENT_INDEX_KEY = "SELECTED_SEGMENT_INDEX_KEY"
    }
}
