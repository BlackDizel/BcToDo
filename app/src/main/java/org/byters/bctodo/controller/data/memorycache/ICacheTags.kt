package org.byters.bctodo.controller.data.memorycache

interface ICacheTags {
    fun getItemsNum(): Int
    fun getItemTitle(position: Int): String
    fun isSelectedWithoutTag(): Boolean
    fun isSelected(position: Int): Boolean

}
