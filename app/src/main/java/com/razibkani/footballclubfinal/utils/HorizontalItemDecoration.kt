package com.razibkani.footballclubfinal.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class HorizontalItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.apply {
            if (parent.getChildAdapterPosition(view) == 0)
                left = spacing

            top = spacing
            right = spacing
            bottom = spacing
        }
    }
}