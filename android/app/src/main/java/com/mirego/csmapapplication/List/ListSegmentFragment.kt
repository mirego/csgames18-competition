package com.mirego.csmapapplication.List


import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirego.csmapapplication.R

class ListSegmentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_list, container, false)
    }

    override fun onStart() {
        super.onStart()

    }
}
