package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.adapter.MappingListAdapter

class ListSegmentFragment : Fragment() {
    var listView: ListView? = null
    var listAdapter: MappingListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view?.findViewById(R.id.listView)
        listView?.adapter = listAdapter
    }


    override fun onResume() {
        super.onResume()
        listView?.adapter = listAdapter
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_list_view, container, false)
        if (listView == null){
            listView = view?.findViewById(R.id.listView)
        }
        return v
    }

    fun setAdapter(adapter: MappingListAdapter){
        listAdapter = adapter
        listView?.adapter = adapter
    }
}
