package com.myapp.mvvmexample.feature.walkthrough.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.myapp.mvvmexample.R
import com.myapp.mvvmexample.feature.walkthrough.view.adapter.WalkThroughPageAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_walkthrough.*
import kotlinx.android.synthetic.main.include_header_action_right.*

class WalkThroughFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_walkthrough, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tvHeader.text = getString(R.string.general_header)
        btnRight.text = getString(R.string.action_skip)
        btnRight.visibility = View.VISIBLE
        val adapter = WalkThroughPageAdapter(childFragmentManager, activity!!)
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)

        btnRight.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_walkThorughPageFragment_to_loginFragment))
    }
}