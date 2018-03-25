package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.component.MyAdapter
import com.mirego.csmapapplication.helper.Item
import com.mirego.csmapapplication.model.Repo
import kotlinx.android.synthetic.main.fragment_list.*

class ListSegmentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Test", "RIP")
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recycleView.setHasFixedSize(true)


        // use a linear layout manager
        recycleView.setLayoutManager(LinearLayoutManager(context))

        // specify an adapter (see also next example)

        var items = listOf<Item>()
        val mAdapter = MyAdapter(items)
        recycleView.adapter = mAdapter
    }
}
