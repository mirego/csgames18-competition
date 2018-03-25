package com.mirego.csmapapplication.activity


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageButton
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.mirego.csmapapplication.MapPingApplication
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.fragment.ListSegmentFragment
import com.mirego.csmapapplication.fragment.MapSegmentFragment
import com.mirego.csmapapplication.model.Mapping
import com.mirego.csmapapplication.service.MappingService
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import javax.inject.Inject


class MainActivity : FragmentActivity() {
    val PERMISSION_REQUEST_ID = 1

    private val listFragment = ListSegmentFragment()
    private val mapFragment = MapSegmentFragment()
    private var selectedSegmentIndex = 0

    private lateinit var segmentButtons: List<ImageButton>
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject
    lateinit var mappingService: MappingService

    var mappings: List<Mapping> = emptyList<Mapping>()
    var lastLocation: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MapPingApplication).netComponent.inject(this)

        segmentButtons = listOf(listButton, mapButton, arButton)

        setActionBar(toolbar)

        setupButtons()

        setUpData()
    }

    private fun setUpData() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_REQUEST_ID)
        } else {
            fusedLocationClient.lastLocation
                    .addOnSuccessListener {
                        lastLocation = LatLng(it.latitude, it.longitude)
                        getMappings()
                    }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_ID -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        fusedLocationClient.lastLocation
                                .addOnSuccessListener {
                                    lastLocation = LatLng(it.latitude, it.longitude)
                                    getMappings()
                                }
                    }

                } else {
                    alert("Please enable location.").show()
                }
            }

            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun getMappings() {
        mappingService.getAll()
                .subscribeOn(Schedulers.newThread())
                .subscribe({ m ->
                    mappings = m
                    runOnUiThread { setupMainView() }
                })
    }

    private fun setupMainView() {
        val args = Bundle()
        args.putParcelableArrayList("mappings", ArrayList(mappings))
        args.putParcelable("location", lastLocation)
        listFragment.arguments = args
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
        val args = Bundle()
        args.putParcelableArrayList("mappings", ArrayList(mappings))
        args.putParcelable("location", lastLocation)
        fragment.arguments = args
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
