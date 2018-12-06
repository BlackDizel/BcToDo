package org.byters.bctodo.controller.data.util

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.controller.data.memorycache.ICacheNotesListener
import org.byters.bctodo.controller.data.memorycache.ICacheTags
import org.byters.bctodo.controller.data.memorycache.callback.ICacheTagListener
import org.byters.bctodo.controller.data.util.callback.IHelperNotesSelectedListener
import org.byters.bctodo.model.ModelNote
import java.util.*
import javax.inject.Inject

class HelperNotesSelected(app: ApplicationToDo) :
    IHelperNotesSelected {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    @Inject
    lateinit var cacheTags: ICacheTags

    private var query: String? = null

    private var folderId: String? = null

    private val listenerCacheTags: ICacheTagListener

    private var listenerCacheNotes: ICacheNotesListener

    private var data: List<ModelNote>? = null

    private var listeners: WeakHashMap<String, IHelperNotesSelectedListener>? = null

    init {
        app.component.inject(this)
        listenerCacheTags = ListenerCacheTags()
        cacheTags.addListener(listenerCacheTags)
        listenerCacheNotes = ListenerCacheNotes()
        cacheNotes.addListener(listenerCacheNotes)
    }

    override fun getItemsNum(): Int = getItems().size

    private fun getItems(): List<ModelNote> {
        if (data == null)
            data = cacheNotes.getItems(cacheTags.getSelectedIds(), cacheTags.isSelectedWithoutTag(), query, folderId)
        if (data == null)
            data = ArrayList()
        return data!!
    }

    override fun getItemTitleSingleLine(position: Int): String? = getItems().opt(position)?.getTitleSingleLine()

    override fun setSelectedNote(adapterPosition: Int) = cacheNotes.setSelectedNote(getItems().opt(adapterPosition)?.id)

    override fun getItemTitle(position: Int): String? = getItems().opt(position)?.title

    override fun getItemBody(position: Int): String? = getItems().opt(position)?.body

    override fun getItemDate(position: Int): Long? = getItems().opt(position)?.date

    override fun addListener(listenerHelperNotes: IHelperNotesSelectedListener) {
        if (listeners == null) listeners = WeakHashMap()
        listeners!!.put(listenerHelperNotes::class.java.name, listenerHelperNotes)
    }


    override fun setFolderId(folderId: String?) {
        this.folderId = folderId
        data = null

        listeners?.values?.forEach { it.onDataUpdated() }
    }

    override fun getFolderId(): String? = folderId


    override fun setQuery(query: String?) {
        this.query = query
        data = null
        listeners?.values?.forEach { it.onDataUpdated() }
    }

    inner class ListenerCacheTags : ICacheTagListener {
        override fun onDataUpdate() {
            data = null
            listeners?.values?.forEach { it.onDataUpdated() }
        }
    }


    inner class ListenerCacheNotes : ICacheNotesListener {
        override fun onDataUpdate() {
            data = null
            listeners?.values?.forEach { it.onDataUpdated() }
        }
    }
}
