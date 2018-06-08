package com.razibkani.footballclubfinal.ui.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.ui.matches.nextmatch.NextMatchListFragment
import com.razibkani.footballclubfinal.ui.matches.prevmatch.PrevMatchListFragment
import com.razibkani.footballclubfinal.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_matches.*

class MatchesFragment : Fragment() {

    companion object {
        val TAG: String = MatchesFragment::class.java.simpleName

        fun newInstance(): MatchesFragment = MatchesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val models = listOf(
                ViewPagerAdapter.Model(getString(R.string.title_tab_last), PrevMatchListFragment.newInstance()),
                ViewPagerAdapter.Model(getString(R.string.title_tab_next), NextMatchListFragment.newInstance())
        )
        val matchesAdapter = ViewPagerAdapter(models, childFragmentManager)
        viewPager.adapter = matchesAdapter

        tabs.setupWithViewPager(viewPager)
    }
}
