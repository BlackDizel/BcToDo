package org.byters.bctodo.view.presenter

interface IPresenterListNotesAdapter {
    fun getItemsNum(): Int
    fun getItemTitleSingleLine(position: Int): String?
    fun onClick(adapterPosition: Int)

}
