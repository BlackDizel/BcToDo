package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.controller.data.memorycache.callback.ICacheTagListener

interface ICacheTags {
    fun getItemsNum(): Int
    fun getItemTitle(position: Int): String
    fun isSelectedWithoutTag(): Boolean
    fun isSelected(position: Int): Boolean
    fun setSelectedWithoutTag(param: Boolean)
    fun setSelected(position: Int, param: Boolean)
    fun addListener(listener: ICacheTagListener)
    fun getSelectedIds(): Iterable<String>?
    fun getId(position: Int): String?
    fun addTag(title: String, color:Int?)
    fun removeTag(id: String)
    fun setSelectedPopup(position: Int)
    fun getSelectedPopupId(): String?
    fun updateTitle(selectedPopupId: String?, title: String)
    fun getItemColor(position: Int): Int?
}
