package com.mirego.csmapapplication.component

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
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
class LostObjectsAdapter(private val objects: ArrayList<String>, private val activity: FragmentActivity) : BaseAdapter() {

    override fun getView(i: Int, p0: View?, viewGroup: ViewGroup?): View {
        var view = p0
        if (view == null) {
            view = View.inflate(activity,R.layout.item,null)
        }


        val s = this.getItem(i) as String

        val img = view!!.findViewById(R.id.imageIcon) as ImageView
        var description = view!!.findViewById(R.id.txtDescription) as TextView
        var position = view!!.findViewById(R.id.txtPosition) as TextView
        var distance = view!!.findViewById(R.id.txtDistance) as TextView
        var name = view!!.findViewById(R.id.txtObjectName) as TextView

        description.text = "..."
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
        return objects.size;
    }
}