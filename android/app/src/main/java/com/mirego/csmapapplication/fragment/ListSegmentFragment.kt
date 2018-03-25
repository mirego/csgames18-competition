package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.mirego.csmapapplication.R

class ListSegmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val vesselPartListView = findViewById(R.id.vesselPartListView) as ListView
        //vesselPartListView.adapter = ListExampleAdapter(this)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}
