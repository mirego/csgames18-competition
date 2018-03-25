package com.mirego.csmapapplication.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.model.Part
import android.support.v7.widget.CardView

class ListSegmentFragment : Fragment() {
    private var baseCard: CardView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseCard = view.findViewById(R.id.baseCard)
    }

    fun onPartListLoad(partList: List<Part>?) {
        for (part in partList!!) {
            // var newCard = baseCard // TODO need a deep copy of basCard then change its attributes
            // view!!.findViewById<ConstraintLayout>(R.id.frag_list).addView(newCard)
        }
    }

}
