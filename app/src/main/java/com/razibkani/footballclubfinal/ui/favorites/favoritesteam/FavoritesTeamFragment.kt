package com.razibkani.footballclubfinal.ui.favorites.favoritesteam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.detailteam.DetailTeamActivity
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.VerticalItemDecoration
import com.razibkani.footballclubfinal.utils.hide
import com.razibkani.footballclubfinal.utils.visible
import kotlinx.android.synthetic.main.fragment_favorites_team.*
import kotlinx.android.synthetic.main.layout_loading_indicator.*
import javax.inject.Inject

class FavoritesTeamFragment : Fragment(), FavoritesTeamMvpView {

    @Inject
    lateinit var presenter: FavoritesTeamPresenter
    @Inject
    lateinit var favTeamAdapter: FavoritesTeamAdapter

    companion object {
        val TAG: String = FavoritesTeamFragment::class.java.simpleName

        fun newInstance(): FavoritesTeamFragment = FavoritesTeamFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).activityComponent()?.inject(this)
        presenter.attachView(this)

        initAdapter()
        initUI()
    }

    override fun onStart() {
        super.onStart()
        presenter.getFavoritesTeam()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        loadingIndicator.visible()
    }

    override fun hideLoading() {
        loadingIndicator.hide()
    }

    override fun updateData(favoritesTeam: List<FootballTeam>) {
        favTeamAdapter.updateData(favoritesTeam)
    }

    private fun initAdapter() {
        favTeamAdapter.onItemClickListener = object : OnItemClickListener<FootballTeam> {
            override fun onClick(position: Int, item: FootballTeam) {
                DetailTeamActivity.start(context, item)
            }
        }
    }

    private fun initUI() {
        listFavTeam.layoutManager = LinearLayoutManager(context)
        listFavTeam.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listFavTeam.adapter = favTeamAdapter
    }
}