package org.byters.bctodo.controller.data.memorycache

import android.text.TextUtils
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.device.ICacheStorage
import org.byters.bctodo.controller.data.util.getTitleSingleLine
import org.byters.bctodo.controller.data.util.opt
import org.byters.bctodo.model.FileEnum
import org.byters.bctodo.model.ModelNote
import org.byters.bctodo.model.ModelNoteCollection
import java.util.*
import javax.inject.Inject

class CacheNotes(app: ApplicationToDo) : ICacheNotes {

    @Inject
    lateinit var cacheStorage: ICacheStorage

    init {
        app.component.inject(this)
    }

    var data: ModelNoteCollection? = null

    private var selectedNoteId: String? = null

    private var listeners: WeakHashMap<String, ICacheNotesListener>? = null

    fun checkData(): ModelNoteCollection {

        if (data == null) {
            data = cacheStorage.readFile(FileEnum.CACHE_NOTES, ModelNoteCollection::class)
        }

        if (data == null) {
            data = ModelNoteCollection(null)
        }
        return data!!
    }


    override fun add(title: String?, body: String?, selectedIds: ArrayList<String>?) {
        if (title == null && body == null) return

        if (checkData().notes == null)
            checkData().notes = ArrayList()
        checkData().notes!!.add(
            ModelNote(
                UUID.randomUUID().toString(),
                title,
                body,
                System.currentTimeMillis(),
                selectedIds
            )
        )

        saveData()
        notifyListeners()
    }

    override fun addListener(listener: ICacheNotesListener) {
        if (listeners == null) listeners = WeakHashMap()
        listeners!!.put(listener::class.java.name, listener)
    }

    private fun notifyListeners() {
        listeners?.values?.forEach { it.onDataUpdate() }
    }

    private fun saveData() {
        cacheStorage.writeData(checkData(), FileEnum.CACHE_NOTES)
    }

    override fun getItemsNum(): Int = checkData().notes?.size ?: 0

    override fun getItems(
        selectedIds: Iterable<String>?,
        selectedWithoutTag: Boolean,
        query: String?
    ): List<ModelNote>? {
        return checkData().notes?.filter {
            (selectedIds != null && (it.tags?.intersect(selectedIds)?.any() ?: false)
                    || selectedWithoutTag && (it.tags?.size == 0 || it.tags == null))
                    && (TextUtils.isEmpty(query)
                    || it.title?.toLowerCase()?.contains(query!!.toLowerCase()) ?: false
                    || it.body?.toLowerCase()?.contains(query!!?.toLowerCase()) ?: false)
        }?.toList()
    }

    override fun getItemTitleSingleLine(position: Int): String? = checkData().notes?.opt(position)?.getTitleSingleLine()


    override fun setSelectedNote(adapterPosition: Int) {
        setSelectedNote(checkData().notes?.opt(adapterPosition)?.id)
    }

    override fun setSelectedNote(id: String?) {
        selectedNoteId = id
    }

    override fun getBodySelected(): String? = checkData().notes?.find { it.id.equals(selectedNoteId) }?.body

    override fun getTitleSelected(): String? = checkData().notes?.find { it.id.equals(selectedNoteId) }?.title

    override fun removeSelected() {
        checkData().notes?.removeAll { it.id.equals(selectedNoteId) }
        saveData()
        notifyListeners()
    }

    override fun editSelected(title: String, body: String) {
        val item = checkData().notes?.find { it.id.equals(selectedNoteId) } ?: return
        item.title = title
        item.body = body
        saveData()
        notifyListeners()
    }

    override fun getItemTitle(position: Int): String? = checkData().notes?.opt(position)?.title

    override fun getItemBody(position: Int): String? = checkData().notes?.opt(position)?.body
    override fun getItemDate(position: Int): Long? = checkData().notes?.opt(position)?.date

    override fun removeTags(id: String) {
        checkData().notes?.forEach {
            it.tags?.removeAll { it.equals(id) }
        }
        saveData()
        notifyListeners()
    }

}
