package com.razibkani.footballclubfinal.ui.homepage

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.favorites.FavoritesFragment
import com.razibkani.footballclubfinal.ui.matches.MatchesFragment
import com.razibkani.footballclubfinal.ui.search.SearchType
import com.razibkani.footballclubfinal.ui.search.searchmatches.SearchMatchesActivity
import com.razibkani.footballclubfinal.ui.search.searchteam.SearchTeamActivity
import com.razibkani.footballclubfinal.ui.teams.TeamsFragment
import com.razibkani.footballclubfinal.utils.inTransaction
import kotlinx.android.synthetic.main.activity_homepage.*

class HomepageActivity : BaseActivity() {

    private var isSearchable: Boolean = true
    private var searchType: String? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragmentContainer, MatchesFragment.newInstance(), MatchesFragment.TAG)
                }
                searchType = SearchType.MATCHES
                refreshOptionMenu(true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragmentContainer, TeamsFragment.newInstance(), TeamsFragment.TAG)
                }
                searchType = SearchType.TEAM
                refreshOptionMenu(true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragmentContainer, FavoritesFragment.newInstance(), FavoritesFragment.TAG)
                }
                searchType = null
                refreshOptionMenu(false)
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
        searchType = SearchType.MATCHES
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return if (isSearchable) {
            menuInflater.inflate(R.menu.menu_search, menu)
            true
        } else {
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.search -> {
                if (searchType == SearchType.MATCHES) {
                    SearchMatchesActivity.start(this)
                } else {
                    SearchTeamActivity.start(this)
                }
                overridePendingTransition(R.anim.slide_up, R.anim.no_anim)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshOptionMenu(isSearch: Boolean) {
        isSearchable = isSearch
        invalidateOptionsMenu()
    }
}
