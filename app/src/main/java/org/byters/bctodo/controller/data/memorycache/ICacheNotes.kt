package org.byters.bctodo.controller.data.memorycache

interface ICacheNotes {
    fun getItemsNum(): Int
    fun getItemTitleSingleLine(position: Int): String?

    fun add(title: String?, body: String?)
}
