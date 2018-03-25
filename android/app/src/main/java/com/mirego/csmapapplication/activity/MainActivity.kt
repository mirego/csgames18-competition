package com.mirego.csmapapplication.activity
import android.os.Bundle;

import android.content.Intent
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
import com.mirego.csmapapplication.model.DataTask
import com.mirego.csmapapplication.model.Repo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject
import com.mirego.csmapapplication.service.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : FragmentActivity() {

    private val listFragment = ListSegmentFragment()
    private val mapFragment = MapSegmentFragment()
    private var selectedSegmentIndex = 0

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

        var d:DataTask= DataTask()
        var json=d.execute().get();
        val b=Bundle ();
        b.putString("json",json.toString());



    }


    private fun downloadData() {

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
