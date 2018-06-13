package com.razibkani.footballclubfinal.ui.detailplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.Player
import com.razibkani.footballclubfinal.ui.base.BaseActivity
import com.razibkani.footballclubfinal.utils.loadUrl
import kotlinx.android.synthetic.main.activity_detail_player.*
import kotlinx.android.synthetic.main.content_detail_player.*

class DetailPlayerActivity : BaseActivity() {

    private var player: Player? = null

    companion object {
        val TAG: String = DetailPlayerActivity::class.java.simpleName
        private const val ARG_PLAYER = "arg_player"

        fun start(context: Context?, player: Player) {
            val intent = Intent(context, DetailPlayerActivity::class.java)
            intent.putExtra(ARG_PLAYER, player)

            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        initToolbar()

        player = intent?.getParcelableExtra(ARG_PLAYER)
        bindView(player)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
    }

    private fun bindView(player: Player?) {
        player?.let {
            supportActionBar?.title = it.strPlayer
            imageHeader.loadUrl(it.strThumb)
            textWeight.text = it.strWeight
            textHeight.text = it.strHeight
            textPosition.text = it.strPosition
            textDescription.text = it.strDescriptionEN
        }
    }
}