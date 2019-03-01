package com.myapp.mvvmexample.core.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


abstract class BaseHolder<T>(parent: ViewGroup, layoutID: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutID, parent, false)) {

    abstract fun bindData(model: T)
}