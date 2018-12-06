package org.byters.bctodo.controller.data.util

import org.byters.bctodo.controller.data.util.callback.IHelperNotesSelectedListener

interface IHelperNotesSelected {
    fun getItemsNum(): Int
    fun getItemTitleSingleLine(position: Int): String?
    fun setSelectedNote(adapterPosition: Int)
    fun getItemTitle(position: Int): String?
    fun getItemBody(position: Int): String?
    fun getItemDate(position: Int): Long?
    fun addListener(listenerHelperNotes: IHelperNotesSelectedListener)
    fun setQuery(query: String?)
    fun setFolderId(folderId: String?)
    fun getFolderId(): String?

}
