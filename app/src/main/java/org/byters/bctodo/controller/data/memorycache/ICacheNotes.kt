package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.model.ModelNote

interface ICacheNotes {
    fun getItemsNum(): Int
    fun getItemTitleSingleLine(position: Int): String?

    fun add(title: String?, body: String?, selectedIds: List<String>?)
    fun setSelectedNote(adapterPosition: Int)
    fun getTitleSelected(): String?
    fun getBodySelected(): String?
    fun removeSelected()
    fun editSelected(title: String, body: String)
    fun getItemTitle(position: Int): String?
    fun getItemBody(position: Int): String?
    fun getItemDate(position: Int): Long?
    fun getItems(selectedIds: Iterable<String>?, selectedWithoutTag: Boolean): List<ModelNote>?
    fun setSelectedNote(id: String?)
    fun addListener(listenerCacheNotes: ICacheNotesListener)
}
