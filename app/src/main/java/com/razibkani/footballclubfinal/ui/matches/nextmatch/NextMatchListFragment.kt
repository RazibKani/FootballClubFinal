package com.razibkani.footballclubfinal.ui.matches.nextmatch


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
import kotlinx.android.synthetic.main.fragment_next_match_list.*
import kotlinx.android.synthetic.main.layout_empty_state.*
import kotlinx.android.synthetic.main.layout_league.*
import javax.inject.Inject

class NextMatchListFragment : Fragment(), NextMatchMvpView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: NextMatchPresenter
    @Inject
    lateinit var nextMatchAdapter: NextMatchAdapter
    @Inject
    lateinit var leagueAdapter: LeagueAdapter

    var lastSelected: Int = 0

    companion object {
        val TAG: String = NextMatchListFragment::class.java.simpleName

        fun newInstance(): NextMatchListFragment {
            return NextMatchListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_next_match_list, container, false)
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

    override fun showEmptyState() {
        listNextMatch.hide()
        empty_state.visible()
    }

    override fun onRefresh() {
        getEvents()
    }

    override fun updateDataEvents(eventList: List<Event>) {
        empty_state.hide()
        listNextMatch.visible()
        nextMatchAdapter.updateData(eventList)
    }

    override fun updateDataLeagues(leagues: List<FootballLeague>) {
        leagueAdapter.updateData(leagues)
        leagueAdapter.selectLeague(lastSelected)
    }

    private fun getEvents() {
        presenter.getEvents(leagueAdapter.getLeague(lastSelected).idLeague)
    }

    private fun initAdapter() {
        nextMatchAdapter.onItemClickListener = object : OnItemClickListener<Event> {
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

        listNextMatch.layoutManager = LinearLayoutManager(context)
        listNextMatch.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listNextMatch.adapter = nextMatchAdapter

        swipeRefresh.setOnRefreshListener(this)
    }
}