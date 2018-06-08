package com.razibkani.footballclubfinal.ui.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.razibkani.footballclubfinal.R

class FavoritesFragment : Fragment() {

    companion object {
        val TAG: String = FavoritesFragment::class.java.simpleName

        fun newInstance(): FavoritesFragment = FavoritesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }
}
