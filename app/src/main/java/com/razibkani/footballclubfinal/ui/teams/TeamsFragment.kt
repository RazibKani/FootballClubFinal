package com.razibkani.footballclubfinal.ui.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballLeague
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.detailteam.DetailTeamActivity
import com.razibkani.footballclubfinal.utils.*
import kotlinx.android.synthetic.main.fragment_teams.*
import kotlinx.android.synthetic.main.layout_league.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class TeamsFragment : Fragment(), TeamsMvpView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: TeamsPresenter
    @Inject
    lateinit var teamsAdapter: TeamsAdapter
    @Inject
    lateinit var leagueAdapter: LeagueAdapter

    var lastSelected: Int = 0

    companion object {
        val TAG: String = TeamsFragment::class.java.simpleName

        fun newInstance(): TeamsFragment = TeamsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).activityComponent()?.inject(this)
        presenter.attachView(this)

        initAdapter()
        initUI()

        presenter.getLeagues()
        getTeams()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        swipeRefresh.showRefresh()
    }

    override fun hideLoading() {
        swipeRefresh.dismissRefresh()
    }

    override fun showErrorMessage(message: String) {
        toast(message)
    }

    override fun onRefresh() {
        getTeams()
    }

    override fun updateDataTeams(teamList: List<FootballTeam>) {
        teamsAdapter.updateData(teamList)
    }

    override fun updateDataLeagues(leagues: List<FootballLeague>) {
        leagueAdapter.updateData(leagues)
        leagueAdapter.selectLeague(lastSelected)
    }

    private fun getTeams() {
        presenter.getTeams(leagueAdapter.getLeague(lastSelected).strLeague)
    }

    private fun initAdapter() {
        teamsAdapter.onItemClickListener = object : OnItemClickListener<FootballTeam> {
            override fun onClick(position: Int, item: FootballTeam) {
                DetailTeamActivity.start(context, item)
            }
        }

        leagueAdapter.onItemClickListener = object : OnItemClickListener<FootballLeague> {
            override fun onClick(position: Int, item: FootballLeague) {
                lastSelected = position
                leagueAdapter.selectLeague(position)
                presenter.getTeams(item.strLeague)
            }
        }
    }

    private fun initUI() {
        listLeague.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listLeague.addItemDecoration(HorizontalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listLeague.adapter = leagueAdapter

        listTeams.layoutManager = LinearLayoutManager(context)
        listTeams.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listTeams.adapter = teamsAdapter

        swipeRefresh.setOnRefreshListener(this)
    }
}
