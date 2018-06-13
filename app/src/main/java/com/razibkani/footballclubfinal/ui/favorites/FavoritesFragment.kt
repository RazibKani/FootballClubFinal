package com.razibkani.footballclubfinal.ui.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.ui.favorites.favoritesmatch.FavoritesMatchFragment
import com.razibkani.footballclubfinal.ui.favorites.favoritesteam.FavoritesTeamFragment
import com.razibkani.footballclubfinal.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    companion object {
        val TAG: String = FavoritesFragment::class.java.simpleName

        fun newInstance(): FavoritesFragment = FavoritesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val models = listOf(
                ViewPagerAdapter.Model(getString(R.string.title_tab_matches), FavoritesMatchFragment.newInstance()),
                ViewPagerAdapter.Model(getString(R.string.title_tab_teams), FavoritesTeamFragment.newInstance())
        )
        val favoritesAdapter = ViewPagerAdapter(models, childFragmentManager)
        viewPager.adapter = favoritesAdapter

        tabs.setupWithViewPager(viewPager)
    }
}