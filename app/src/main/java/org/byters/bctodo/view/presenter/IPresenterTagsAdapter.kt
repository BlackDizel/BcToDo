package org.byters.bctodo.view.presenter

interface IPresenterTagsAdapter {
    fun getItemViewType(position: Int): Int
    fun getItemsNum(): Int
    fun getTypeHeader(): Int
    fun onClickHeader()
    fun getItemTitle(position: Int): String
    fun getTypeOther(): Int
    fun isSelected(position: Int): Boolean

}
