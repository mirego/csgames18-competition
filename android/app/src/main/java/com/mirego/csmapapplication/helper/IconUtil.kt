package com.mirego.csmapapplication.helper

import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.model.Part

class IconUtil {
    companion object {
        fun getIconFromPart(part: Part) : Int {
            when (part.type) {
                "battery" -> return R.drawable.ic_part_battery
                "bulb" -> return R.drawable.ic_part_bulb
                "cable" -> return R.drawable.ic_part_cable
                "chain" -> return R.drawable.ic_part_chain
                "clutch" -> return R.drawable.ic_part_clutch
                "coil" -> return R.drawable.ic_part_coil
                "dial" -> return R.drawable.ic_part_dial
                "disk" -> return R.drawable.ic_part_disk
                "fan" -> return R.drawable.ic_part_fan
                "filter" -> return R.drawable.ic_part_filter
                "fuel" -> return R.drawable.ic_part_fuel
                "gear" -> return R.drawable.ic_part_gear
                "generator" -> return R.drawable.ic_part_generator
                "heads" -> return R.drawable.ic_part_heads
                "hose" -> return R.drawable.ic_part_hose
                "mag" -> return R.drawable.ic_part_mag
                "mount" -> return R.drawable.ic_part_mount
                "piston" -> return R.drawable.ic_part_piston
                "plugs" -> return R.drawable.ic_part_plugs
                "radiator" -> return R.drawable.ic_part_radiator
                "regulator" -> return R.drawable.ic_part_regulator
                "reservoir" -> return R.drawable.ic_part_reservoir
                "scanner" -> return R.drawable.ic_part_scanner
                "sensor" -> return R.drawable.ic_part_sensor
                "shaft" -> return R.drawable.ic_part_shaft
                "sink" -> return R.drawable.ic_part_sink
                "sparkling" -> return R.drawable.ic_part_sparkplug
                "turbo" -> return R.drawable.ic_part_turbo
                "valve" -> return R.drawable.ic_part_valve
                "wheel" -> return R.drawable.ic_part_wheel
            }
            return R.drawable.ic_part_bulb
        }
    }
}