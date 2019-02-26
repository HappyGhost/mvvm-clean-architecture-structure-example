package com.myapp.mvvmexample.feature.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapp.mvvmexample.R
import com.myapp.mvvmexample.core.application.Injectable
import dagger.android.support.DaggerFragment


class ArticleListFragment : DaggerFragment(), Injectable {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }
}