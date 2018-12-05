package org.byters.bctodo.controller.data.memorycache

import android.text.TextUtils
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.device.ICacheStorage
import org.byters.bctodo.controller.data.util.opt
import org.byters.bctodo.model.FileEnum
import org.byters.bctodo.model.ModelFolder
import org.byters.bctodo.model.ModelFolderCollection
import java.util.*
import javax.inject.Inject

class CacheFolders(app: ApplicationToDo) : ICacheFolders {

    @Inject
    lateinit var cacheStorage: ICacheStorage

    private var data: ModelFolderCollection? = null

    private var openedIds: HashSet<String>? = null

    init {
        app.component.inject(this)
    }

    override fun getItemsNum(folderId: String?): Int = checkData().data?.filter { it.parentId == folderId }?.size ?: 0

    override fun getFolderIdRoot(): String? = checkData().data?.firstOrNull { TextUtils.isEmpty(it.parentId) }?.id

    override fun getFolderId(parentFolderId: String?, position: Int): String? = checkData().data
        ?.filter { it.parentId == parentFolderId }
        ?.sortedBy { it.id }?.opt(position)?.id

    override fun isFolderOpen(folderId: String?): Boolean = openedIds?.contains(folderId) ?: false

    override fun updateOpened(folderId: String?) {
        if (TextUtils.isEmpty(folderId)) return
        if (openedIds == null) openedIds = HashSet()
        if (openedIds!!.contains(folderId))
            openedIds!!.remove(folderId)
        else
            openedIds!!.add(folderId!!)
    }

    override fun getItemTitle(folderId: String?): String =
        checkData().data?.firstOrNull { it.id == folderId }?.title ?: ""

    override fun addFolder(folderId: String?, title: String) {
        if (checkData().data == null) checkData().data = ArrayList()
        checkData().data!!.add(ModelFolder(UUID.randomUUID().toString(), folderId, title))
        saveData()
    }

    private fun saveData() {
        cacheStorage.writeData(checkData(), FileEnum.CACHE_FOLDERS)
    }

    private fun checkData(): ModelFolderCollection {
        if (data == null) data = cacheStorage.readFile(FileEnum.CACHE_FOLDERS, ModelFolderCollection::class)
        if (data == null) {
            data = ModelFolderCollection()
            addFolder(null, "root")
        }
        return data!!
    }

}
