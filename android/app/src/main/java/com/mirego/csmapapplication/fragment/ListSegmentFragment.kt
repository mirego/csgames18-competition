package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ListView
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.component.LostObjectsAdapter
import kotlinx.android.synthetic.*


class ListSegmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val frameLayout = view.findViewById(R.id.fragmentRoot) as FrameLayout
        val listView = ListView(context)
        val adapter = LostObjectsAdapter(context!!, ArrayList())
        listView.adapter = adapter
        frameLayout.addView(listView)
        return view
    }
}
