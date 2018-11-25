package org.byters.bctodo.controller.data.memorycache

interface ICacheNotes {
    fun getItemsNum(): Int
    fun getItemTitleSingleLine(position: Int): String?

    fun add(title: String?, body: String?)
    fun setSelectedNote(adapterPosition: Int)
    fun getTitleSelected(): String?
    fun getBodySelected(): String?
    fun removeSelected()
    fun editSelected(title: String, body: String)
    fun getItemTitle(position: Int): String?
    fun getItemBody(position: Int): String?
}
