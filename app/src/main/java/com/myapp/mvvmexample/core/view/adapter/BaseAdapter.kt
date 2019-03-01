package com.myapp.mvvmexample.core.view.adapter

import android.support.v7.widget.RecyclerView
import com.myapp.mvvmexample.core.view.adapter.holder.BaseHolder


abstract class BaseAdapter<K, T : BaseHolder<K>>(var itemClicked: AdapterItemClicked<K>) : RecyclerView.Adapter<T>() {
    var sources: List<K> = ArrayList()
    override fun getItemCount(): Int = sources.size

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.itemView.setOnClickListener {
            itemClicked.onItemClicked(sources[position])
        }
    }
}

interface AdapterItemClicked<T> {
    fun onItemClicked(data: T)
}