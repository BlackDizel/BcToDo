package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.device.ICacheStorage
import org.byters.bctodo.controller.data.memorycache.callback.ICacheTagListener
import org.byters.bctodo.controller.data.util.opt
import org.byters.bctodo.model.FileEnum
import org.byters.bctodo.model.ModelTag
import org.byters.bctodo.model.ModelTagsCollection
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class CacheTags(app: ApplicationToDo) : ICacheTags {

    @Inject
    lateinit var cacheStorage: ICacheStorage

    var data: ModelTagsCollection? = null
    var selectedPopupTagId: String? = null

    private var listeners: WeakHashMap<String, ICacheTagListener>? = null

    init {
        app.component.inject(this)
    }

    private fun checkData(): ModelTagsCollection {
        if (data == null) data = cacheStorage.readFile(FileEnum.CACHE_TAGS, ModelTagsCollection::class)
        if (data == null) data = ModelTagsCollection()
        return data!!
    }

    override fun addTag(title: String, color: Int?) {
        if (checkData().tags == null)
            checkData().tags = ArrayList()
        checkData().tags!!.add(ModelTag(title = title, color = color))
        saveData()
        notifyListeners()
    }

    override fun removeTag(id: String) {
        val tag: ModelTag = checkData().tags?.firstOrNull { it.id.equals(id) } ?: return
        if (!checkData().tags!!.remove(tag)) return
        saveData()
        notifyListeners()
    }

    private fun saveData() {
        if (data == null) return
        cacheStorage.writeData(data!!, FileEnum.CACHE_TAGS)
    }

    override fun getItemsNum(): Int = checkData().tags?.size ?: 0

    override fun getItemTitle(position: Int): String = checkData().tags?.opt(position)?.title ?: ""

    override fun getItemColor(position: Int): Int? = checkData().tags?.opt(position)?.color

    override fun isSelectedWithoutTag(): Boolean = checkData().isSelectedWithoutTag

    override fun isSelected(position: Int): Boolean = checkData().tags?.opt(position)?.isSelected ?: false

    override fun setSelected(position: Int, param: Boolean) {
        checkData().tags?.opt(position)?.isSelected = param
        notifyListeners()
    }

    override fun setSelectedWithoutTag(param: Boolean) {
        checkData().isSelectedWithoutTag = param
        notifyListeners()
    }

    private fun notifyListeners() {
        listeners?.values?.forEach { it.onDataUpdate() }
    }

    override fun getSelectedIds(): Iterable<String>? =
        checkData().tags?.filter {
            it.isSelected
        }?.map { it.id }

    override fun getId(position: Int): String? = checkData().tags?.opt(position)?.id

    override fun addListener(listener: ICacheTagListener) {
        if (listeners == null) listeners = WeakHashMap()
        listeners!!.put(listener::class.java.name, listener)
    }

    override fun setSelectedPopup(position: Int) {
        selectedPopupTagId = getId(position)
    }

    override fun getSelectedPopupId(): String? = selectedPopupTagId

    override fun updateTitle(selectedPopupId: String?, title: String, colorSelected: Int?) {
        checkData().tags?.firstOrNull { it.id == selectedPopupId }
            ?.apply { this.title = title; this.color = colorSelected }
        saveData()
        notifyListeners()
    }

}
