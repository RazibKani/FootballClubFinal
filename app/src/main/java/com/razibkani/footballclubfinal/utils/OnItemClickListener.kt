package com.razibkani.footballclubfinal.utils

interface OnItemClickListener<in V> {
    fun onClick(position: Int, item: V)
}