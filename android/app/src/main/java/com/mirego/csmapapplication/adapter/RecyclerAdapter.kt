package com.mirego.csmapapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.extension.inflate
import com.mirego.csmapapplication.model.PartItem

/**
 * Created by Felix on 2018-03-24.
 */
class RecyclerAdapter (private val items: ArrayList<PartItem>)
    : RecyclerView.Adapter<RecyclerAdapter.PartItemViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerAdapter.PartItemViewHolder {
        val inflatedView = parent.inflate(R.layout.listcard, false)
        return PartItemViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.PartItemViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
    }

}