package com.mirego.csmapapplication.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.model.Item
import android.graphics.drawable.Drawable




class ItemListAdapter(private var activity: Activity, private var items: List<Item>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtTitle: TextView? = null
        var txtType: TextView? = null
        var imageView: ImageView? = null

        init {
            this.txtTitle = row?.findViewById(R.id.item_title)
            this.txtType = row?.findViewById(R.id.item_type)
            this.imageView = row?.findViewById(R.id.imageIcon)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_list_item, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = items[position]

        val uri = "@drawable/ic_part_" + item.getType()

        val imageResource = activity.getResources().getIdentifier(uri, null, activity.getPackageName())
        val res = activity.getResources().getDrawable(imageResource, activity.theme)

        viewHolder.imageView?.setImageDrawable(res)
        viewHolder.txtTitle?.text = item.getName()
        viewHolder.txtType?.text = item.getDescription()

        return view as View
    }

    override fun getItem(i: Int): Item {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}