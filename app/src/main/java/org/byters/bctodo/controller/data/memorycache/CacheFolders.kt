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
import kotlin.collections.ArrayList

class CacheFolders(app: ApplicationToDo) : ICacheFolders {

    @Inject
    lateinit var cacheStorage: ICacheStorage

    private var data: ModelFolderCollection? = null

    private var openedIds: HashSet<String>? = null

    private var selectedId: String? = null

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

    override fun addFolder(parentFolderId: String?, title: String) {
        if (checkData().data == null) checkData().data = ArrayList()
        checkData().data!!.add(ModelFolder(UUID.randomUUID().toString(), parentFolderId, title))
        saveData()
    }


    override fun deleteFolder(folderId: String?) {
        if (checkData().data == null) return

        val ids: ArrayList<String?> = ArrayList()
        ids.add(folderId)

        recursiveFindChilds(folderId, ids)

        checkData().data?.removeAll { it.id in ids }

        saveData()
    }

    private fun recursiveFindChilds(parentFolderId: String?, ids: ArrayList<String?>) {
        checkData().data?.forEach {
            if (it.parentId == parentFolderId) {
                ids.add(it.id); recursiveFindChilds(it.id, ids)
            }
        }
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

    override fun setSelectedId(folderId: String?) {
        this.selectedId = folderId
    }

    override fun updateFolder(folderId: String?, title: String) {
        checkData().data?.firstOrNull { it.id == folderId }?.title = title
        saveData()
    }

    override fun getSelectedId(): String? = selectedId


}
