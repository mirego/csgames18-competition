package com.mirego.csmapapplication.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.model.LocationDto

class MappingListAdapter(private var activity: Activity, private var items: ArrayList<LocationDto>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtName: TextView? = null
        var txtComponent: TextView? = null
        var txtLat: TextView? = null
        var txtDistance: TextView? = null
        var iconImage: ImageView? = null

        init {
            txtName = row?.findViewById(R.id.nameTxt)
            txtComponent = row?.findViewById(R.id.componentTxt)
            txtLat = row?.findViewById(R.id.lat_txt)
            txtDistance =  row?.findViewById(R.id.distanceTxt)
            iconImage = row?.findViewById(R.id.imageIcon)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder

        if (convertView == null){
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.fragment_list, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var locationDto = items[position]
        viewHolder.txtComponent?.text = locationDto.component
        viewHolder.txtName?.text = locationDto.name
        viewHolder.txtLat?.text = createCoordinates(locationDto.lat, locationDto.lon)

        val resId = view?.resources?.getIdentifier("ic_part_" + locationDto.type, "drawable", view?.context.packageName)
        resId?.let { viewHolder.iconImage?.setImageResource(it) }

        return view as View
    }

    fun createCoordinates(lat: Double?, long: Double?): String{
        return "%.4f° N, %.4f° W".format(lat, long)
    }

    override fun getItem(i: Int): Any {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}