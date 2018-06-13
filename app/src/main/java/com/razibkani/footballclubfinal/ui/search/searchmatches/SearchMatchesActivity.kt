package com.razibkani.footballclubfinal.ui.search.searchmatches

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.ui.detailmatch.MatchDetailActivity
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.VerticalItemDecoration
import com.razibkani.footballclubfinal.utils.dismissRefresh
import com.razibkani.footballclubfinal.utils.showRefresh
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search_matches.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class SearchMatchesActivity : BaseActivity(), SearchMatchesMvpView, SearchView.OnQueryTextListener {

    @Inject
    lateinit var presenter: SearchMatchesPresenter
    @Inject
    lateinit var searchMatchAdapter: SearchMatchesAdapter

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SearchMatchesActivity::class.java)
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

    override fun updateMatches(matches: List<Event>) {
        searchMatchAdapter.updateData(matches)
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        if (query.isNotEmpty()) {
            presenter.getEventByKeyword(query)
        }
        return true
    }

    private fun initAdapter() {
        searchMatchAdapter.onItemClickListener = object : OnItemClickListener<Event> {
            override fun onClick(position: Int, item: Event) {
                MatchDetailActivity.start(this@SearchMatchesActivity, item)
            }
        }
    }

    private fun initUI() {
        listSearchMatches.layoutManager = LinearLayoutManager(this)
        listSearchMatches.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listSearchMatches.adapter = searchMatchAdapter

        searchView.setOnQueryTextListener(this)
    }
}