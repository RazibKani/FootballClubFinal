package com.razibkani.footballclubfinal.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(private val models: List<Model>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return models[position].fragment
    }

    override fun getCount(): Int {
        return models.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return models[position].title
    }

    data class Model(val title: String, val fragment: Fragment)
}