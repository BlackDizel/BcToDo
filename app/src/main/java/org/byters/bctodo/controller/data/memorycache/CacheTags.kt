package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.BuildConfig
import org.byters.bctodo.controller.data.memorycache.callback.ICacheTagListener
import org.byters.bctodo.controller.data.util.opt
import org.byters.bctodo.model.ModelTag
import org.byters.bctodo.model.ModelTagsCollection
import java.util.*

class CacheTags(app: ApplicationToDo) : ICacheTags {

    var data: ModelTagsCollection? = null

    private var listeners: WeakHashMap<String, ICacheTagListener>? = null

    init {

        if (BuildConfig.DEBUG) {
            data = ModelTagsCollection(ArrayList())
            data!!.tags.add(ModelTag(title = "todo"))
            data!!.tags.add(ModelTag(title = "work"))
            data!!.tags.add(ModelTag(title = "other"))
        }
    }

    override fun getItemsNum(): Int = data?.tags?.size ?: 0

    override fun getItemTitle(position: Int): String = data?.tags?.opt(position)?.title ?: ""

    override fun isSelectedWithoutTag(): Boolean = data?.isSelectedWithoutTag ?: false

    override fun isSelected(position: Int): Boolean = data?.tags?.opt(position)?.isSelected ?: false

    override fun setSelected(position: Int, param: Boolean) {
        data?.tags?.opt(position)?.isSelected = param
        notifyListeners()
    }

    override fun setSelectedWithoutTag(param: Boolean) {
        data?.isSelectedWithoutTag = param
        notifyListeners()
    }

    private fun notifyListeners() {
        listeners?.values?.forEach { it.onDataUpdate() }
    }

    override fun getSelectedIds(): Iterable<String>? =
        data?.tags?.filter {
            it.isSelected
        }?.map { it.id }

    override fun getId(position: Int): String? = data?.tags?.opt(position)?.id

    override fun addListener(listener: ICacheTagListener) {
        if (listeners == null) listeners = WeakHashMap()
        listeners!!.put(listener::class.java.name, listener)
    }
}
