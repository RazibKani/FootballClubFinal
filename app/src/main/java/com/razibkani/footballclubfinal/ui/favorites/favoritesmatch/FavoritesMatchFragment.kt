package com.razibkani.footballclubfinal.ui.favorites.favoritesmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.detailmatch.MatchDetailActivity
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.VerticalItemDecoration
import com.razibkani.footballclubfinal.utils.hide
import com.razibkani.footballclubfinal.utils.visible
import kotlinx.android.synthetic.main.fragment_favorites_match.*
import kotlinx.android.synthetic.main.layout_loading_indicator.*
import javax.inject.Inject

class FavoritesMatchFragment : Fragment(), FavoritesMatchMvpView {

    @Inject
    lateinit var presenter: FavoritesMatchPresenter
    @Inject
    lateinit var favMatchAdapter: FavoritesMatchAdapter

    companion object {
        val TAG: String = FavoritesMatchFragment::class.java.simpleName

        fun newInstance(): FavoritesMatchFragment {
            return FavoritesMatchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites_match, container, false)
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
        presenter.getFavoriteEvents()
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

    override fun updateData(favoriteEvents: List<Event>) {
        favMatchAdapter.updateData(favoriteEvents)
    }

    private fun initAdapter() {
        favMatchAdapter.onItemClickListener = object : OnItemClickListener<Event> {
            override fun onClick(position: Int, item: Event) {
                MatchDetailActivity.start(context, item)
            }
        }
    }

    private fun initUI() {
        listFavMatch.layoutManager = LinearLayoutManager(context)
        listFavMatch.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listFavMatch.adapter = favMatchAdapter
    }
}