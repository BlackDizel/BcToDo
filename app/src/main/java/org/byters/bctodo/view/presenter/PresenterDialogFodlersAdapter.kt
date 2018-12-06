package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheFolders
import org.byters.bctodo.view.presenter.callback.IPresenterDialogFoldersAdapterListener
import java.util.*
import javax.inject.Inject

class PresenterDialogFodlersAdapter(app: ApplicationToDo) : IPresenterDialogFoldersAdapter {

    @Inject
    lateinit var cacheFolders: ICacheFolders

    private var folderAddId: String? = null

    private var listeners: WeakHashMap<String, IPresenterDialogFoldersAdapterListener>? = null

    init {
        app.component.inject(this)
    }

    override fun getItemsNum(folderId: String?): Int = cacheFolders.getItemsNum(folderId)

    override fun getFolderIdRoot(): String? = cacheFolders.getFolderIdRoot()

    override fun getFolderId(parentFolderId: String?, position: Int): String? =
        cacheFolders.getFolderId(parentFolderId, position)

    override fun getFolderTitle(folderId: String?, position: Int): String =
        cacheFolders.getItemTitle(getFolderId(folderId, position))

    override fun isVisibleFolderAdd(folderId: String?, position: Int): Boolean =
        getFolderId(folderId, position).equals(folderAddId)

    override fun isVisibleFolders(folderId: String?, position: Int): Boolean =
        cacheFolders.isFolderOpen(getFolderId(folderId, position))

    override fun isVisibleFolderShow(folderId: String?, position: Int): Boolean =
        getItemsNum(getFolderId(folderId, position)) > 0

    override fun addListener(listenerPresenter: IPresenterDialogFoldersAdapterListener) {
        if (listeners == null) listeners = WeakHashMap()
        listeners!!.put(listenerPresenter.getName(), listenerPresenter)
    }

    override fun onClickFolderShow(folderId: String?, adapterPosition: Int) {
        cacheFolders.updateOpened(getFolderId(folderId, adapterPosition))
        listeners?.values?.forEach { it.updateData() }
    }

    override fun onClickFolderAdd(folderId: String?, position: Int) {
        folderAddId = getFolderId(folderId, position)
        listeners?.values?.forEach { it.updateData() }
    }

    override fun onClickDelete(folderId: String?, adapterPosition: Int) {
        cacheFolders.deleteFolder(getFolderId(folderId, adapterPosition))
        listeners?.values?.forEach { it.updateData() }
    }

    override fun onClickEdit(folderId: String?, adapterPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickFolderAddCancel() {
        folderAddId = null
        listeners?.values?.forEach { it.updateData() }
    }

    override fun onClickFolderAddComplete(
        title: String,
        folderId: String?,
        adapterPosition: Int
    ) {
        folderAddId = null
        cacheFolders.addFolder(getFolderId(folderId, adapterPosition), title)
        listeners?.values?.forEach { it.updateData() }
    }

}

