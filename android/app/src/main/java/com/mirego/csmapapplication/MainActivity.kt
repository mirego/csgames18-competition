package com.mirego.csmapapplication


import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageButton
import com.mirego.csmapapplication.fragment.ListSegmentFragment
import com.mirego.csmapapplication.fragment.MapSegmentFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private val listFragment = ListSegmentFragment()
    private val mapFragment = MapSegmentFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setActionBar(toolbar)

        setupButtons()
        setupMainView()
    }

    private fun setupMainView() {
        supportFragmentManager.beginTransaction()
            .add(fragmentRoot.id, listFragment)
            .commit()
    }

    private fun onSegmentButtonClicked(button: ImageButton) {

        when (button) {
            listButton -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(fragmentRoot.id, listFragment)
                transaction.commit()
            }

            mapButton -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(fragmentRoot.id, mapFragment)
                transaction.commit()
            }

            arButton -> {

            }
        }

    }

    private fun setupButtons() {
        val buttons = listOf<ImageButton>(listButton, mapButton, arButton)

        val listener = View.OnClickListener { view: View ->
            for (button in buttons) {
                if (button == view as ImageButton) {
                    button.setColorFilter(ContextCompat.getColor(this, R.color.brightSunYellow))
                    onSegmentButtonClicked(button)
                } else {
                    button.setColorFilter(ContextCompat.getColor(this, R.color.cloudGray))
                }
            }
        }

        for (button in buttons) {
            button.setOnClickListener(listener)
        }
    }

}
