package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheFolders
import org.byters.bctodo.controller.data.util.IHelperNotesSelected
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.presenter.callback.IPresenterDialogFoldersAdapterListener
import org.byters.bctodo.view.ui.dialog.callback.IDialogFolderMoreListener
import java.util.*
import javax.inject.Inject

class PresenterDialogFodlersAdapter(app: ApplicationToDo) : IPresenterDialogFoldersAdapter {

    @Inject
    lateinit var cacheFolders: ICacheFolders

    @Inject
    lateinit var navigator: INavigator

    @Inject
    lateinit var helperNotes: IHelperNotesSelected

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

    private fun folderAdd(folderId: String?) {
        folderAddId = folderId
        listeners?.values?.forEach { it.updateData() }
    }

    private fun folderDelete(folderId: String?) {
        cacheFolders.deleteFolder(folderId)
        listeners?.values?.forEach { it.updateData() }
    }

    override fun onClickMore(folderId: String?, adapterPosition: Int) {
        cacheFolders.setSelectedId(getFolderId(folderId, adapterPosition))
        navigator.navigateFolderOptions(ListenerFolderEdit())
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

    inner class ListenerFolderEdit : IDialogFolderMoreListener {
        override fun onDelete() {
            folderDelete(cacheFolders.getSelectedId())
        }

        override fun onEdit(name: String) {
            cacheFolders.updateFolder(cacheFolders.getSelectedId(), name)
            listeners?.values?.forEach { it.updateData() }
        }

        override fun onAdd() {
            folderAdd(cacheFolders.getSelectedId())
        }

    }

    override fun onClickItem(folderId: String?, adapterPosition: Int) {
        helperNotes.setFolderId(getFolderId(folderId, adapterPosition))
    }


}

