package com.myapp.mvvmexample.feature.detail.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.myapp.business.rss.article.info.ArticleInfo
import com.myapp.mvvmexample.R
import com.myapp.mvvmexample.core.util.DateUtil
import com.myapp.mvvmexample.core.view.BaseFragment
import com.myapp.mvvmexample.feature.list.viewmodel.share.ArticleShareViewModel
import kotlinx.android.synthetic.main.fragment_article_detail.*
import kotlinx.android.synthetic.main.include_header_app_bar.*


class ArticleDetailFragment : BaseFragment() {

    lateinit var articleShareViewModel: ArticleShareViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHeader()
        initViewModel()
        showArticleInformation()
    }

    private fun initHeader() {
        imgBack.setOnClickListener { view ->
            Navigation.findNavController(view).popBackStack()
        }

    }

    private fun showArticleInformation() {
        articleShareViewModel.articleLiveData.observe(this, Observer { item ->
            run {
                bindToView(item)
            }
        })
    }

    private fun bindToView(item: ArticleInfo?) {
        tvHeader.text = item?.title
        tvArticleTitle.text = item?.title
        tvDate.text = DateUtil.format(item?.date, DateUtil.DD_MM_YYYY_FORMAT)
        tvDescription.text = item?.description
        Glide.with(this).load(item?.thumbnailUrl).into(imgThumbnail)
    }

    private fun initViewModel() {
        articleShareViewModel = activity?.run {
            ViewModelProviders.of(activity!!).get(ArticleShareViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
}