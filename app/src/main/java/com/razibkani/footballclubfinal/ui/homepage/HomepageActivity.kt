package com.razibkani.footballclubfinal.ui.homepage

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.favorites.favoritesmatch.FavoritesMatchFragment
import com.razibkani.footballclubfinal.ui.matches.MatchesFragment
import com.razibkani.footballclubfinal.utils.inTransaction
import kotlinx.android.synthetic.main.activity_homepage.*

class HomepageActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragmentContainer, MatchesFragment.newInstance(), MatchesFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                supportFragmentManager.inTransaction {
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragmentContainer, FavoritesMatchFragment.newInstance(), FavoritesMatchFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.inTransaction {
            replace(R.id.fragmentContainer, MatchesFragment.newInstance(), MatchesFragment.TAG)
        }
    }
}
