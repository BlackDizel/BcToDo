package org.byters.bctodo.view.presenter

interface IPresenterDialogTagListAdapter {
    fun getItemViewType(position: Int): Int
    fun getItemsNum(): Int
    fun getTypeTagAdd(): Int
    fun onClickAdd(title: String)
    fun getItemTitle(position: Int): String?
    fun setListener(listenerPresenter: IPresenterDialogTagListAdapterListener)
    fun onClickMore(adapterPosition: Int)
    fun getColorLabelTagCreate(): Int?
    fun onClickLabelColor()

}
