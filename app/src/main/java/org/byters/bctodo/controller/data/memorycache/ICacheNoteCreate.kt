package org.byters.bctodo.controller.data.memorycache

interface ICacheNoteCreate {
    fun isSelected(id: String?): Boolean
    fun setSelected(id: String?, value: Boolean)
    fun getSelectedIds(): ArrayList<String>?
    fun setColor(color: Int)
    fun getColorLabel(): Int?
}
