package org.byters.bctodo.view.presenter

import org.byters.bctodo.model.FontEnum
import org.byters.bctodo.model.StyleEnum
import org.byters.bctodo.view.presenter.callback.IPresenterListNotesAdapterListener

interface IPresenterListNotesAdapter {
    fun getItemsNum(): Int
    fun getItemTitleSingleLine(position: Int): String?
    fun onClick(adapterPosition: Int)
    fun setListener(listenerPresenter: IPresenterListNotesAdapterListener)
    fun getStyle(): StyleEnum
    fun getItemTitle(position: Int): String?
    fun getItemBody(position: Int): String?
    fun getItemDate(position: Int): String?
    fun getItemFont(): FontEnum

}
