package com.myapp.mvvmexample.feature.walkthrough.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.myapp.mvvmexample.R
import com.myapp.mvvmexample.feature.walkthrough.view.WalkThroughPageFragment

class WalkThroughPageAdapter(fm: FragmentManager, var context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(pos: Int): Fragment {
        return when (pos) {
            0 -> WalkThroughPageFragment.newPage(context.getString(R.string.walk_through_page_1_content))
            1 -> WalkThroughPageFragment.newPage(context.getString(R.string.walk_through_page_2_content))
            2 -> WalkThroughPageFragment.newPage(context.getString(R.string.walk_through_page_3_content))
            else -> WalkThroughPageFragment.newPage(context.getString(R.string.walk_through_page_1_content))
        }
    }

    override fun getCount(): Int = 3

}