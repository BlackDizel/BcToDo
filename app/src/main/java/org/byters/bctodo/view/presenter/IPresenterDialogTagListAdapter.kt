package org.byters.bctodo.view.presenter

interface IPresenterDialogTagListAdapter {
    fun getItemViewType(position: Int): Int
    fun getItemsNum(): Int
    fun getTypeTagAdd(): Int
    fun onClickAdd(title: String)
    fun onClickDelete(adapterPosition: Int)
    fun getItemTitle(position: Int): String?
    fun setListener(listenerPresenter: IPresenterDialogTagListAdapterListener)

}
