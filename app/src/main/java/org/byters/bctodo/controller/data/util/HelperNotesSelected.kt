package org.byters.bctodo.controller.data.util

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.controller.data.memorycache.ICacheTags
import org.byters.bctodo.controller.data.memorycache.callback.ICacheTagListener
import org.byters.bctodo.controller.data.util.callback.IHelperNotesSelectedListener
import org.byters.bctodo.model.ModelNote
import java.lang.ref.WeakReference
import javax.inject.Inject

class HelperNotesSelected(app: ApplicationToDo) :
    IHelperNotesSelected {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    @Inject
    lateinit var cacheTags: ICacheTags

    private val listenerCacheTags: ICacheTagListener

    private var data: List<ModelNote>? = null

    private var refListener: WeakReference<IHelperNotesSelectedListener>? = null

    init {
        app.component.inject(this)
        listenerCacheTags = ListenerCacheTags()
        cacheTags.addListener(listenerCacheTags)
    }

    override fun getItemsNum(): Int = getItems()?.size ?: 0

    private fun getItems(): List<ModelNote> {
        if (data == null)
            data = cacheNotes.getItems(cacheTags.getSelectedIds(), cacheTags.isSelectedWithoutTag())
        if (data == null)
            data = ArrayList()
        return data!!
    }

    override fun getItemTitleSingleLine(position: Int): String? = getItems().opt(position)?.getTitleSingleLine()

    override fun setSelectedNote(adapterPosition: Int) = cacheNotes.setSelectedNote(getItems().opt(adapterPosition)?.id)

    override fun getItemTitle(position: Int): String? = getItems().opt(position)?.title

    override fun getItemBody(position: Int): String? = getItems().opt(position)?.body

    override fun getItemDate(position: Int): Long? = getItems().opt(position)?.date

    override fun setListener(listenerHelperNotes: IHelperNotesSelectedListener) {
        refListener = WeakReference(listenerHelperNotes)
    }

    inner class ListenerCacheTags : ICacheTagListener {
        override fun onDataUpdate() {
            data = null
            refListener?.get()?.onDataUpdated()
        }
    }
}