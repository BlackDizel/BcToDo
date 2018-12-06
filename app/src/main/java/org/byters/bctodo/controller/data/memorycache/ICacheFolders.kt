package org.byters.bctodo.controller.data.memorycache

interface ICacheFolders {
    fun getItemsNum(folderId: String?): Int
    fun getFolderId(parentFolderId: String?, position: Int): String?
    fun isFolderOpen(folderId: String?): Boolean
    fun updateOpened(folderId: String?)
    fun getItemTitle(folderId: String?): String
    fun addFolder(folderId: String?, title: String)
    fun getFolderIdRoot(): String?
    fun deleteFolder(folderId: String?)
    fun setSelectedId(folderId: String?)
    fun updateFolder(selectedId: String?, name: String)
    fun getSelectedId(): String?

}
