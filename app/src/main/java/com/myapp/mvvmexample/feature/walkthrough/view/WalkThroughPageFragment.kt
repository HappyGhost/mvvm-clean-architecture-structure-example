package com.myapp.mvvmexample.feature.walkthrough.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapp.mvvmexample.R
import kotlinx.android.synthetic.main.fragment_walkthrough_page.*

class WalkThroughPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_walkthrough_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvContent.text = arguments?.getString(KEY_PAGER_CONTENT)
    }

    companion object {
        const val KEY_PAGER_CONTENT = "pagerContent"
        fun newPage(content: String): WalkThroughPageFragment {
            var fragment = WalkThroughPageFragment()
            var bundle = Bundle()
            bundle.putString(KEY_PAGER_CONTENT, content)
            fragment.arguments = bundle
            return fragment
        }
    }
}