package com.mirego.csmapapplication.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.helper.CustomAdapter
import com.mirego.csmapapplication.model.Piece
import kotlinx.android.synthetic.main.fragment_list.*

class ListSegmentFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var tempModel = ArrayList<Piece>();
        tempModel.add(Piece("test", "Tester", "1", "type", 14.5f, 15.3f, "address!"))
        tempModel.add(Piece("test2", "Tester2", "2", "type2", 14.5f, 15.3f, "address2!"))

        (lsPieces as ListView).adapter = CustomAdapter(tempModel, context);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}
