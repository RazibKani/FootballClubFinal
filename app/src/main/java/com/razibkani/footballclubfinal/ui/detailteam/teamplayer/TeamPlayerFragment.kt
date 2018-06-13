package com.razibkani.footballclubfinal.ui.detailteam.teamplayer


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.Player
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.detailplayer.DetailPlayerActivity
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.VerticalItemDecoration
import com.razibkani.footballclubfinal.utils.dismissRefresh
import com.razibkani.footballclubfinal.utils.showRefresh
import kotlinx.android.synthetic.main.fragment_team_player.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class TeamPlayerFragment : Fragment(), TeamPlayerMvpView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: TeamPlayerPresenter
    @Inject
    lateinit var teamPlayerAdapter: TeamPlayerAdapter

    private var teamName: String? = null

    companion object {
        val TAG: String = TeamPlayerFragment::class.java.simpleName
        private const val ARG_TEAM_NAME = "arg_team_name"

        fun newInstance(teamName: String?) : TeamPlayerFragment {
            val fragment = TeamPlayerFragment()
            val bundle = Bundle()
            bundle.putString(ARG_TEAM_NAME, teamName)

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamName = arguments?.getString(ARG_TEAM_NAME)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).activityComponent()?.inject(this)
        presenter.attachView(this)

        initAdapter()
        initUI()

        getPlayers()
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
        getPlayers()
    }

    override fun updateData(playerList: List<Player>) {
        teamPlayerAdapter.updateData(playerList)
    }

    private fun initAdapter() {
        teamPlayerAdapter.onItemClickListener = object : OnItemClickListener<Player> {
            override fun onClick(position: Int, item: Player) {
                DetailPlayerActivity.start(context, item)
            }
        }
    }

    private fun getPlayers() {
        teamName?.let { presenter.getPlayers(it) }
    }

    private fun initUI() {
        listTeamPlayer.layoutManager = LinearLayoutManager(context)
        listTeamPlayer.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listTeamPlayer.adapter = teamPlayerAdapter

        swipeRefresh.setOnRefreshListener(this)
    }
}