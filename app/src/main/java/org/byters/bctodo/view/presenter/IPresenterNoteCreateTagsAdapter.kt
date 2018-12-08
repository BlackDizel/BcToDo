package org.byters.bctodo.view.presenter

import org.byters.bctodo.view.presenter.callback.IPresenterNoteCreateTagsAdapterListener

interface IPresenterNoteCreateTagsAdapter {
    fun getItemType(position: Int): Int
    fun getItemsNum(): Int
    fun getTypeSettings(): Int
    fun getItemTitle(position: Int): String
    fun isSelected(position: Int): Boolean
    fun onClickItem(adapterPosition: Int)
    fun onClickSettings()
    fun setListener(listenerPresenter: IPresenterNoteCreateTagsAdapterListener)
    fun getItemColor(position: Int): Int?

}
