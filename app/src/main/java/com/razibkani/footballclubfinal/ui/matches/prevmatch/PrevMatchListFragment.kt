package com.razibkani.footballclubfinal.ui.matches.prevmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.data.model.FootballLeague
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.detailmatch.MatchDetailActivity
import com.razibkani.footballclubfinal.utils.*
import kotlinx.android.synthetic.main.fragment_prev_match_list.*
import kotlinx.android.synthetic.main.layout_league.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class PrevMatchListFragment : Fragment(), PrevMatchListMvpView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: PrevMatchListPresenter
    @Inject
    lateinit var prevMatchAdapter: PrevMatchAdapter
    @Inject
    lateinit var leagueAdapter: LeagueAdapter

    var lastSelected: Int = 0

    companion object {
        val TAG: String = PrevMatchListFragment::class.java.simpleName

        fun newInstance(): PrevMatchListFragment {
            return PrevMatchListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_prev_match_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).activityComponent()?.inject(this)
        presenter.attachView(this)

        initAdapter()
        initUI()

        presenter.getLeagues()
        getEvents()
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
        getEvents()
    }

    override fun updateDataEvents(eventList: List<Event>) {
        prevMatchAdapter.updateData(eventList)
    }

    override fun updateDataLeagues(leagues: List<FootballLeague>) {
        leagueAdapter.updateData(leagues)
        leagueAdapter.selectLeague(lastSelected)
    }

    private fun getEvents() {
        presenter.getEvents(leagueAdapter.getLeague(lastSelected).idLeague)
    }

    private fun initAdapter() {
        prevMatchAdapter.onItemClickListener = object : OnItemClickListener<Event> {
            override fun onClick(position: Int, item: Event) {
                MatchDetailActivity.start(context, item)
            }
        }

        leagueAdapter.onItemClickListener = object : OnItemClickListener<FootballLeague> {
            override fun onClick(position: Int, item: FootballLeague) {
                lastSelected = position
                leagueAdapter.selectLeague(position)
                presenter.getEvents(item.idLeague)
            }
        }
    }

    private fun initUI() {
        listLeague.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listLeague.addItemDecoration(HorizontalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listLeague.adapter = leagueAdapter

        listPrevMatch.layoutManager = LinearLayoutManager(context)
        listPrevMatch.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listPrevMatch.adapter = prevMatchAdapter

        swipeRefresh.setOnRefreshListener(this)
    }
}