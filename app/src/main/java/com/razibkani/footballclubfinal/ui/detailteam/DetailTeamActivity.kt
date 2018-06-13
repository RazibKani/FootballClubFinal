package com.razibkani.footballclubfinal.ui.detailteam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.detailteam.teamplayer.TeamPlayerFragment
import com.razibkani.footballclubfinal.ui.detailteam.teamprofile.TeamProfileFragment
import com.razibkani.footballclubfinal.utils.ViewPagerAdapter
import com.razibkani.footballclubfinal.utils.loadUrl
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class DetailTeamActivity : BaseActivity(), DetailTeamMvpView, AppBarLayout.OnOffsetChangedListener {

    @Inject
    lateinit var presenter: DetailTeamPresenter

    private var team: FootballTeam? = null

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    companion object {
        val TAG: String = DetailTeamActivity::class.java.simpleName
        private const val ARG_TEAM = "arg_team"

        fun start(context: Context?, team: FootballTeam) {
            val intent = Intent(context, DetailTeamActivity::class.java)
            intent.putExtra(ARG_TEAM, team)

            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        activityComponent()?.inject(this)
        presenter.attachView(this)

        initToolbar()

        team = intent?.getParcelableExtra(ARG_TEAM)
        bindView(team)

        team?.idTeam?.let {
            isFavorite = presenter.favoriteState(it)
        }

        val models = listOf(
                ViewPagerAdapter.Model(getString(R.string.title_tab_overview), TeamProfileFragment.newInstance(team?.strDescriptionEN)),
                ViewPagerAdapter.Model(getString(R.string.title_tab_player), TeamPlayerFragment.newInstance(team?.strTeam))
        )
        val matchesAdapter = ViewPagerAdapter(models, supportFragmentManager)
        viewPager.adapter = matchesAdapter

        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) {
                    team?.idTeam?.let { presenter.removeFromFavorite(it) }
                } else {
                    team?.let { presenter.addToFavorite(it) }
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showSuccessMessage(successMessage: String) {
        toast(successMessage)
    }

    override fun showErrorMessage(errorMessage: String) {
        toast(errorMessage)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
    }

    private fun bindView(team: FootballTeam?) {
        team?.let {
            imageHeader.loadUrl(team.strTeamFanart)
            imageTeam.loadUrl(team.strTeamBadge)
            textTeamName.text = team.strTeam
            textTeamFormed.text = team.intFormedYear
            textTeamStadium.text = team.strStadium
        }

        appbar.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        when {
            Math.abs(verticalOffset) >= appBarLayout!!.totalScrollRange -> {
                supportActionBar?.title = team?.strTeam
            }
            else -> {
                supportActionBar?.title = ""
            }
        }
    }
}