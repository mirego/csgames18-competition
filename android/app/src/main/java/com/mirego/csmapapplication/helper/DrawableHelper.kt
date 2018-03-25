package com.mirego.csmapapplication.helper

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.annotation.DrawableRes
import android.support.v4.content.res.ResourcesCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.mirego.csmapapplication.R

object DrawableHelper {
    fun getDrawableByType(type: String): Int {
        if (type == "battery") {
            return R.drawable.ic_part_battery
        } else if (type == "bulb") {
            return R.drawable.ic_part_bulb
        } else if (type == "cable") {
            return R.drawable.ic_part_cable
        } else if (type == "chain") {
            return R.drawable.ic_part_chain
        } else if (type == "clutch") {
            return R.drawable.ic_part_clutch
        } else if (type == "coil") {
            return R.drawable.ic_part_coil
        } else if (type == "dial") {
            return R.drawable.ic_part_dial
        } else if (type == "disk") {
            return R.drawable.ic_part_disk
        } else if (type == "fan") {
            return R.drawable.ic_part_fan
        } else if (type == "filter") {
            return R.drawable.ic_part_filter
        } else if (type == "fuel") {
            return R.drawable.ic_part_fuel
        } else if (type == "gear") {
            return R.drawable.ic_part_gear
        } else if (type == "generator") {
            return R.drawable.ic_part_generator
        } else if (type == "heads") {
            return R.drawable.ic_part_heads
        } else if (type == "hose") {
            return R.drawable.ic_part_hose
        } else if (type == "mag") {
            return R.drawable.ic_part_mag
        } else if (type == "mount") {
            return R.drawable.ic_part_mount
        } else if (type == "piston") {
            return R.drawable.ic_part_piston
        } else if (type == "plugs") {
            return R.drawable.ic_part_plugs
        } else if (type == "radiator") {
            return R.drawable.ic_part_radiator
        } else if (type == "regulator") {
            return R.drawable.ic_part_regulator
        } else if (type == "reservoir") {
            return R.drawable.ic_part_reservoir
        } else if (type == "scanner") {
            return R.drawable.ic_part_scanner
        } else if (type == "sensor") {
            return R.drawable.ic_part_sensor
        } else if (type == "shaft") {
            return R.drawable.ic_part_shaft
        } else if (type == "sink") {
            return R.drawable.ic_part_sink
        } else if (type == "sparkplug") {
            return R.drawable.ic_part_sparkplug
        } else if (type == "turbo") {
            return R.drawable.ic_part_turbo
        } else if (type == "valve") {
            return R.drawable.ic_part_valve
        } else if (type == "wheel") {
            return R.drawable.ic_part_wheel
        }
        return 0
    }


    fun createPinForPart(@DrawableRes partResId: Int, resources: Resources): BitmapDescriptor {
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