package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirego.csmapapplication.R
import android.R.array
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import com.mirego.csmapapplication.adapter.RecyclerAdapter
import com.mirego.csmapapplication.model.PartItem
import kotlinx.android.synthetic.main.fragment_list.*
import java.math.BigDecimal


class ListSegmentFragment : Fragment() {

    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var adapter : RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        val list = arrayListOf(PartItem("", "", "", "", BigDecimal(1), BigDecimal(1), ""))
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        return recyclerView
    }
}
