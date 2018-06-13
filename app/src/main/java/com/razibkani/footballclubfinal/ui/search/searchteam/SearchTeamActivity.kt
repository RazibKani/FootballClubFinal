package com.razibkani.footballclubfinal.ui.search.searchteam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.detailteam.DetailTeamActivity
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.VerticalItemDecoration
import com.razibkani.footballclubfinal.utils.dismissRefresh
import com.razibkani.footballclubfinal.utils.showRefresh
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class SearchTeamActivity : BaseActivity(), SearchTeamMvpView, SearchView.OnQueryTextListener {

    @Inject
    lateinit var presenter: SearchTeamPresenter
    @Inject
    lateinit var searchTeamAdapter: SearchTeamAdapter

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SearchTeamActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activityComponent()?.inject(this)
        presenter.attachView(this)

        initAdapter()
        initUI()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_anim, R.anim.slide_down)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showLoading() {
        swipeRefresh.showRefresh()
        swipeRefresh.isEnabled = true
    }

    override fun hideLoading() {
        swipeRefresh.dismissRefresh()
        swipeRefresh.isEnabled = false
    }

    override fun showErrorMessage(message: String) {
        toast(message)
    }

    override fun updateTeams(teams: List<FootballTeam>) {
        searchTeamAdapter.updateData(teams)
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        if (query.isNotEmpty()) {
            presenter.getTeamByName(query)
        }
        return true
    }

    private fun initAdapter() {
        searchTeamAdapter.onItemClickListener = object : OnItemClickListener<FootballTeam> {
            override fun onClick(position: Int, item: FootballTeam) {
                DetailTeamActivity.start(this@SearchTeamActivity, item)
            }
        }
    }

    private fun initUI() {
        textSearchType.text = getString(R.string.search_for_team)

        listSearch.layoutManager = LinearLayoutManager(this)
        listSearch.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listSearch.adapter = searchTeamAdapter

        searchView.setOnQueryTextListener(this)
    }
}