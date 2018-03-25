package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.google.android.gms.maps.model.LatLng
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.model.Mapping

class ListSegmentFragment : Fragment() {
    var mappings = arrayListOf<Mapping>()
    var currentLocation = LatLng(0.0, 0.0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            mappings = it.getParcelableArrayList<Mapping>("mappings")
            currentLocation = it.getParcelable<LatLng>("location")
        }
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val listView = view.findViewById<ListView>(R.id.listView)

        listView.adapter = ListViewAdapter(activity!!, mappings, currentLocation)

        return view
    }
}
