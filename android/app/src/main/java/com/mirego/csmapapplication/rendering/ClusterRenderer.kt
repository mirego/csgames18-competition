package com.mirego.csmapapplication.rendering

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.mirego.csmapapplication.helper.DrawableHelper
import com.mirego.csmapapplication.model.PointClusterItem

class ClusterRenderer(
        var context: Context,
        map: GoogleMap,
        clusterManager: ClusterManager<PointClusterItem>) : DefaultClusterRenderer<PointClusterItem>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(item: PointClusterItem?, markerOptions: MarkerOptions?) {
        item?.let {
            markerOptions?.icon(DrawableHelper.createPinForPart(it.getIcon(), context.resources))
        }
        super.onBeforeClusterItemRendered(item, markerOptions)
    }
}