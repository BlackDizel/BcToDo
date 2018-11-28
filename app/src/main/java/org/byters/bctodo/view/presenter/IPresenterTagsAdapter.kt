package org.byters.bctodo.view.presenter

import org.byters.bctodo.view.presenter.callback.IPresenterTagsAdapterListener

interface IPresenterTagsAdapter {
    fun getItemViewType(position: Int): Int
    fun getItemsNum(): Int
    fun getTypeHeader(): Int
    fun onClickHeader()
    fun getItemTitle(position: Int): String
    fun getTypeOther(): Int
    fun isSelected(position: Int): Boolean
    fun onClickItem(adapterPosition: Int)
    fun setListener(listenerPresenter: IPresenterTagsAdapterListener)

}
