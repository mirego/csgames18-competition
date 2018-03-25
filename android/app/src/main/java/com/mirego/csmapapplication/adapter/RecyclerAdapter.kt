package com.mirego.csmapapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.extension.inflate
import com.mirego.csmapapplication.model.PartItem
import kotlinx.android.synthetic.main.list_card.view.*

/**
 * Created by Felix on 2018-03-24.
 */
class RecyclerAdapter (private val items: ArrayList<PartItem>)
    : RecyclerView.Adapter<RecyclerAdapter.PartItemViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerAdapter.PartItemViewHolder {
        val inflatedView = parent.inflate(R.layout.list_card, false)
        return PartItemViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.PartItemViewHolder, position: Int) {
        val partItem = items[position]
        holder.bindPartItem(partItem)
    }

    class PartItemViewHolder (v : View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var partItem: PartItem? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            //Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            private val PART_ITEM_KEY = "PART_ITEM"
        }

        fun bindPartItem(part: PartItem) {
            this.partItem = part

            view.nameText.text = part.name
            view.componentText.text = part.component
            view.coordsText.text = part.lat.toString() + "° N, " + part.lon.toString() + "° W"
        }
    }

}