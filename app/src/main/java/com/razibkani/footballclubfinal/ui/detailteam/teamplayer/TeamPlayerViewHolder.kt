package com.razibkani.footballclubfinal.ui.detailteam.teamplayer

import android.support.v7.widget.RecyclerView
import android.view.View
import com.razibkani.footballclubfinal.data.model.Player
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.loadUrl
import kotlinx.android.synthetic.main.item_player.view.*

class TeamPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(player: Player, onClickListener: OnItemClickListener<Player>?) {
        itemView?.apply {
            itemView.imagePlayer.loadUrl(player.strCutout)
            itemView.textPlayerName.text = player.strPlayer
            itemView.textPlayerPosition.text = player.strPosition

            onClickListener?.let {
                setOnClickListener { onClickListener.onClick(adapterPosition, player) }
            }
        }
    }
}