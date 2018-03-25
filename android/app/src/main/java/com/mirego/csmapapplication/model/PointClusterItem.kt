package com.mirego.csmapapplication.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.mirego.csmapapplication.helper.DrawableHelper

class PointClusterItem(val mapping: Mapping) : ClusterItem {

    fun getIcon(): Int = DrawableHelper.getDrawableByType(mapping.type)

    override fun getPosition(): LatLng {
        if (mapping.lat != null && mapping.lon != null) {
            return LatLng(mapping.lat, mapping.lon)
        }
        return LatLng(0.0, 0.0)
    }
}