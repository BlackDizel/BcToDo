package org.byters.bctodo.controller.data.util

fun <T> ArrayList<T>.opt(position: Int): T? =
    if (position < 0 || this.size <= position) null else this.get(position)
