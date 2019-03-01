package com.myapp.mvvmexample.feature.list.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.myapp.business.core.callback.Status
import com.myapp.business.rss.article.info.ArticleInfo
import com.myapp.mvvmexample.R
import com.myapp.mvvmexample.core.application.Injectable
import com.myapp.mvvmexample.core.view.BaseFragment
import com.myapp.mvvmexample.core.view.adapter.AdapterItemClicked
import com.myapp.mvvmexample.feature.list.view.adapter.ArticleListAdapter
import com.myapp.mvvmexample.feature.list.viewmodel.ArticleListViewModel
import com.myapp.mvvmexample.feature.list.viewmodel.share.ArticleShareViewModel
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.android.synthetic.main.include_header_action_right.*
import javax.inject.Inject


class ArticleListFragment : BaseFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var articleListViewModel: ArticleListViewModel

    lateinit var articleShareViewModel: ArticleShareViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvHeader.text = getString(R.string.general_header)
        btnRight.visibility = View.VISIBLE
        btnRight.text = getString(R.string.action_logout)
        btnRight.setOnClickListener { viewClicked ->
            Navigation.findNavController(viewClicked).navigate(R.id.action_articleListFragment_to_loginFragment)
        }
        initViewModel()
        initArticleRecycle()
    }

    private fun initViewModel() {
        articleListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ArticleListViewModel::class.java)

        articleShareViewModel = activity?.run {
            ViewModelProviders.of(activity!!).get(ArticleShareViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    private fun initArticleRecycle() {
        var adapter = ArticleListAdapter(object : AdapterItemClicked<ArticleInfo> {
            override fun onItemClicked(data: ArticleInfo) {
                articleShareViewModel.selectArticleInfo(data)
                Navigation.findNavController(rvArticle)
                    .navigate(R.id.action_articleListFragment_to_articleDetailFragment)
            }
        })
        rvArticle.adapter = adapter
        rvArticle.layoutManager = LinearLayoutManager(context)
        articleListViewModel.articleListLiveData().observe(viewLifecycleOwner, Observer { response ->
            run {
                when {
                    response?.status == Status.SUCCESS -> {
                        hideProgressDialog()
                        if (response.data != null) {
                            adapter.sources = response.data!!
                            adapter.notifyDataSetChanged()
                        }
                    }
                    response?.status == Status.ERROR -> {
                        hideProgressDialog()
                    }
                    response?.status == Status.LOADING -> {
                        showProcessDialog()
                    }
                }
            }
        })
        swipeRefreshArticle.setOnRefreshListener {
            if (swipeRefreshArticle.isRefreshing) {
                articleListViewModel.refresh()
                swipeRefreshArticle.isRefreshing = false
            }
        }
    }


}