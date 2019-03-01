package com.myapp.mvvmexample.feature.list.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.myapp.business.rss.article.info.ArticleInfo
import com.myapp.mvvmexample.R
import com.myapp.mvvmexample.core.util.DateUtil
import com.myapp.mvvmexample.core.view.adapter.holder.BaseHolder
import kotlinx.android.synthetic.main.item_article.view.*


class ArticleListHolder(parent: ViewGroup, layoutId: Int) : BaseHolder<ArticleInfo>(parent, layoutId) {
    override fun bindData(model: ArticleInfo) {
        itemView.tvTitle.text = model.title
        itemView.tvDate.text = DateUtil.format(model.date, DateUtil.DD_MM_YYYY_FORMAT)
        itemView.tvDescription.text = model.description
        Glide.with(itemView.context).load(model.thumbnailUrl).into(itemView.imageIcon)
    }

}

class ArticleListAdapter : RecyclerView.Adapter<ArticleListHolder>() {
    var datas: List<ArticleInfo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ArticleListHolder {
        return ArticleListHolder(parent, R.layout.item_article)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ArticleListHolder, position: Int) {
        holder.bindData(datas[position])
    }

}