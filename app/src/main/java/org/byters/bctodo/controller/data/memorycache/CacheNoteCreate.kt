package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.ApplicationToDo

class CacheNoteCreate(app: ApplicationToDo) : ICacheNoteCreate {
    private var selectedIds: HashSet<String>? = null

    override fun isSelected(id: String?): Boolean = selectedIds?.contains(id) ?: false

    override fun setSelected(id: String?, value: Boolean) {
        if (id == null) return
        if (selectedIds == null && !value) return
        if (selectedIds == null) selectedIds = HashSet()
        if (value)
            selectedIds!!.add(id)
        else selectedIds!!.remove(id)
    }

    override fun getSelectedIds(): List<String>? = selectedIds?.toList()

}
