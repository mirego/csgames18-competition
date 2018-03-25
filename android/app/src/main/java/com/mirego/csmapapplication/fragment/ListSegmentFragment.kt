package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.component.LostObjectsAdapter


class ListSegmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val listView = view.findViewById(R.id.listContainer) as ListView

        val list = ArrayList<String>()
        var adapterlist : ArrayAdapter<String>?=null
        list.add("Hello")
        list.add("Hello 1")
        list.add("Hello 2")
        adapterlist = ArrayAdapter(activity!!,android.R.layout.simple_list_item_1,list)
        val adapter = LostObjectsAdapter(list, activity!!)
        listView.adapter = adapter
        return view
    }
}
