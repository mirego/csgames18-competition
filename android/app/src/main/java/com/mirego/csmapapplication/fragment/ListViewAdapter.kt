package com.mirego.csmapapplication.fragment

import android.app.Activity
import android.location.Location
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.google.android.gms.maps.model.LatLng
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.helper.DrawableHelper
import com.mirego.csmapapplication.model.Mapping
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ListViewAdapter(val activity: Activity, var mappings: List<Mapping>, val currentLocation: LatLng) : BaseAdapter() {

    init {
        mappings = mappings
                .map {
                    if (it.lat != null && it.lon != null) {
                        var values = FloatArray(10)
                        Location.distanceBetween(currentLocation.latitude, currentLocation.longitude, it.lat, it.lon, values)
                        it.distance = values[0] / 1000
                    } else {
                        it.distance = Float.MAX_VALUE
                    }
                    it
                }
                .sortedBy { it.distance }
    }

    override fun getView(i: Int, v: View?, parent: ViewGroup?): View {
        val item = mappings.get(i)
        return with(parent!!.context) {
            cardView {
                layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
                linearLayout {
                    gravity = Gravity.CENTER_VERTICAL

                    imageView(getDrawable(DrawableHelper.getDrawableByType(item.type))) {
                        scaleType = ImageView.ScaleType.FIT_CENTER
                        background = getDrawable(R.drawable.imageview_copper_border)
                        padding = dip(12)
                    }.lparams(width = dip(70), height = dip(70))

                    verticalLayout {
                        textView(item.name) {
                            maxLines = 1
                            textAppearance = R.style.partTitle
                            textColor = R.color.purpleBrown
                        }.lparams(width = matchParent, height = matchParent)

                        textView(item.component) {
                            textColor = R.color.purpleBrown
                        }.lparams(width = wrapContent, height = wrapContent)

                        if (item.distance != null && item.distance != Float.MAX_VALUE) {
                            verticalLayout {
                                textView("${item.lat}° N, ${item.lon}° W") {
                                    maxLines = 1
                                    textColor = R.color.brownishGrey
                                }.lparams(width = wrapContent, height = wrapContent)
                                textView("(${item.distance} km)") {
                                    maxLines = 1
                                    textColor = R.color.brownishGrey
                                }.lparams(width = wrapContent, height = wrapContent)
                            }.lparams(width = matchParent, height = wrapContent) {
                                topMargin = dip(3)
                            }
                        }
                    }.lparams(height = wrapContent, width = matchParent) {
                        marginStart = dip(16)
                    }

                }.lparams(height = matchParent, width = matchParent) {
                    margin = dip(20)
                }
            }
        }
    }

    override fun getItem(i: Int): Any {
        return mappings.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong();
    }

    override fun getCount(): Int {
        return mappings.size;
    }
}