package com.mirego.csmapapplication.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mirego.csmapapplication.R
import android.widget.ImageView
import android.widget.TextView





/**
 * Created by Sagold on 2018-03-24.
 */
class LostObjectsAdapter(private val context: Context, private val objects: ArrayList<String>) : BaseAdapter() {

    override fun getView(i: Int, p0: View?, viewGroup: ViewGroup?): View {
        var view = p0
        if (view == null) {
            view = LayoutInflater.from(viewGroup!!.context).inflate(R.layout.fragment_list, viewGroup, false)
        }

        val s = this.getItem(i) as String

        val img = view!!.findViewById(R.id.imageIcon) as ImageView
        val description = view!!.findViewById(R.id.txtDescription) as TextView
        val position = view!!.findViewById(R.id.txtPosition) as TextView
        val distance = view!!.findViewById(R.id.txtDistance) as TextView
        val name = view!!.findViewById(R.id.txtObjectName) as TextView

        description.text = "ici"
        position.text = "jhdfhg"
        distance.text = "hjbfdh"
        name.text = "djfbhgbf"

        return view
    }

    override fun getItem(p0: Int): Any {
        return objects.get(p0)
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getCount(): Int {
        return objects.size
    }
}