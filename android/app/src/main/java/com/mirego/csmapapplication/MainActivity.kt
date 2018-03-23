package com.mirego.csmapapplication


import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import com.mirego.csmapapplication.List.ListSegmentFragment
import com.mirego.csmapapplication.Ar.ArSegmentFragment
import com.mirego.csmapapplication.Map.MapSegmentFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private val listFragment = ListSegmentFragment()
    private val mapFragment = MapSegmentFragment()
    private val arFragment = ArSegmentFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtons()
        setupMainView()
    }

    private fun setupMainView() {
        supportFragmentManager.beginTransaction()
                .add(fragmentRoot.id, listFragment)
                .commit()
    }

    private fun didTouchUp(button: ImageButton) {
        val transaction = supportFragmentManager.beginTransaction()
        when (button) {
            listButton -> {
                transaction.replace(fragmentRoot.id, listFragment)
            }

            mapButton -> {
                transaction.replace(fragmentRoot.id, mapFragment)
            }

            arButton -> {
                transaction.replace(fragmentRoot.id, arFragment)
            }
        }
        transaction.commit()
    }

    private fun setupButtons() {
        val buttons = listOf<ImageButton>(listButton, mapButton, arButton)

        val listener = View.OnClickListener { v: View ->
            for (b in buttons) {
                if (b == v as ImageButton) {
                    b.setColorFilter(ContextCompat.getColor(this, R.color.brightSunYellow))
                    didTouchUp(b)
                } else {
                    b.setColorFilter(ContextCompat.getColor(this, R.color.cloudGray))
                }
            }
        }

        for (btn in buttons) {
            btn.setOnClickListener(listener)
        }
    }

}
