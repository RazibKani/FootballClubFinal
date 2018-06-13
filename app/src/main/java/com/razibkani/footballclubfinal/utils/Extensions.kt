package com.razibkani.footballclubfinal.utils

import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.razibkani.footballclubfinal.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by razibkani on 26/04/18.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun SwipeRefreshLayout.showRefresh() {
    isRefreshing = true
}

fun SwipeRefreshLayout.dismissRefresh() {
    isRefreshing = false
}

fun ImageView.loadUrl(imageUrl: String?) {
    if (imageUrl != null) {
        Picasso.with(this.context).load(imageUrl).into(this)
    } else {
        this.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.ic_placeholder))
    }
}

fun TextView.setFormattedDate(date: String?) {
    date?.let {
        val oldDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date)
        val newDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale("in", "ID")).format(oldDate)

        text = newDate
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}