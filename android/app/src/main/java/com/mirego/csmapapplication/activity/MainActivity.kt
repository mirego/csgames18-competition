package com.mirego.csmapapplication.activity


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ListAdapter
import android.widget.ListView
import com.mirego.csmapapplication.MapPingApplication
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.adapters.ItemListAdapter
import com.mirego.csmapapplication.fragment.ListSegmentFragment
import com.mirego.csmapapplication.fragment.MapSegmentFragment
import com.mirego.csmapapplication.model.Item
import com.mirego.csmapapplication.model.LongLat
import com.mirego.csmapapplication.service.ItemService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import android.location.LocationListener
import android.support.v4.app.ActivityCompat


class MainActivity : FragmentActivity() {

    private val listFragment = ListSegmentFragment()
    private val mapFragment = MapSegmentFragment()
    private var selectedSegmentIndex = 0

    private var items: List<Item>? = null
    private var adapter: ListAdapter? = null

    private val mainActivity = this

    private var coords = LongLat()
    private val MY_PERMISSIONS_REQUEST: Int = 123

    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager
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

        coords.lon = "0"
        coords.lat = "0"

        setupButtons()

        downloadData()

        locationListener = object : LocationListener {
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String?) {}
            override fun onProviderDisabled(provider: String?) {}
            override fun onLocationChanged(location: Location) {
                coords.lat = location.latitude.toString()
                coords.lon = location.longitude.toString()
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600,
                    500f, locationListener)
        }else{
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST)
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    locationManager = getSystemService(LOCATION_SERVICE) as LocationManager


                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600,
                                500f, locationListener)
                    }
                }
            }
        }
    }

    private fun downloadData() {
        retrofit.create(ItemService::class.java).listItems(coords).enqueue(object : Callback<List<Item>> {
            override fun onFailure(call: Call<List<Item>>?, t: Throwable?) {
                Log.d("fetch info", "failed to fetch items")
            }

            override fun onResponse(call: Call<List<Item>>?, response: Response<List<Item>>?) {
                Log.d("fetch info", "fetched items successfully")
                items = response?.body()
                adapter = ItemListAdapter(mainActivity, items?: emptyList())
                refreshList()
            }
        })
    }

    fun refreshList() {
        if(adapter != null){
            (findViewById<ListView>(R.id.itemList)).adapter = this.adapter
        }
    }

    private fun setupMainView() {
        supportFragmentManager.beginTransaction()
            .add(fragmentRoot.id, listFragment)
            .commit()
    }

    private fun onSegmentButtonClicked(button: ImageButton) {
        when (button) {
            listButton -> {
                replaceListFragment(listFragment)
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

    private fun replaceListFragment(fragment: Fragment){
        replaceFragment(fragment)
        supportFragmentManager.executePendingTransactions()
        refreshList()
    }

    fun gotoPointOnMap(item: Item){
        replaceFragment(mapFragment)
        supportFragmentManager.executePendingTransactions()
        mapFragment.setPinLocation(item.getLon(), item.getLat(), item.getName(), item.getType())
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
