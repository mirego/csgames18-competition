package com.mirego.csmapapplication.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.model.Part
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_list.*

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

        val adapter = CartsAdapter(this, partList)
        lvPart?.adapter = adapter
    }

    inner class CartsAdapter : BaseAdapter {

        private var partsList: List<Part>? = null
        private var context: ListSegmentFragment? = null

        constructor(context: ListSegmentFragment, partsList: List<Part>) : super() {
            this.partsList = partsList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.list_item, parent, false)
                vh = ViewHolder(view)
                view?.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            val item = getItem(position)
            vh.title.text = item.name
            vh.component.text = item.component

            vh.localization.text = if (item.latitude == null || item.longitude == null) {
                "N/A° N, N/A° W"
            } else {
                "${String.format("%.4f", item.latitude)}° N, ${String.format("%.4f", item.longitude)}° W"
            }

            vh.distance.text = if (item.distance == null || item.distance == Float.MAX_VALUE) {
                "(N/A km)"
            } else {
                "(${String.format("%.2f", item.distance!! / 1000)} km)"
            }

            return view
        }

        override fun getItem(position: Int): Part {
            return partsList!!.elementAt(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return partsList?.size!!
        }
    }
    private class ViewHolder(view: View?) {
        val title: TextView = view?.findViewById<TextView>(R.id.title) as TextView
        val component: TextView = view?.findViewById<TextView>(R.id.component) as TextView
        val localization: TextView = view?.findViewById<TextView>(R.id.localization) as TextView
        val distance: TextView = view?.findViewById<TextView>(R.id.distance) as TextView
    }
}
