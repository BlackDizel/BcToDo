package org.byters.bctodo.view.presenter

import org.byters.bctodo.view.presenter.callback.IPresenterDialogFoldersAdapterListener

interface IPresenterDialogFoldersAdapter {
    fun getItemsNum(folderId: String?): Int
    fun getFolderId(parentFolderId: String?, position: Int): String?
    fun getFolderTitle(folderId: String?, position: Int): String
    fun addListener(listenerPresenter: IPresenterDialogFoldersAdapterListener)
    fun isVisibleFolderAdd(folderId: String?, position: Int): Boolean
    fun isVisibleFolders(folderId: String?, position: Int): Boolean
    fun onClickFolderShow(folderId: String?, adapterPosition: Int)
    fun onClickFolderAddCancel()
    fun onClickFolderAddComplete(title: String, folderId: String?, adapterPosition: Int)
    fun getFolderIdRoot(): String?
    fun isVisibleFolderShow(folderId: String?, position: Int): Boolean
    fun onClickMore(folderId: String?, adapterPosition: Int)
    fun onClickItem(folderId: String?, adapterPosition: Int)
    fun onCreate()

}
