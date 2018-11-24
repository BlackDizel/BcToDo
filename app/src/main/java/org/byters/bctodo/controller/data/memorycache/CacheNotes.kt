package org.byters.bctodo.controller.data.memorycache

import android.text.TextUtils
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.util.opt
import org.byters.bctodo.model.ModelNote
import org.byters.bctodo.model.ModelNoteCollection
import java.util.*

class CacheNotes(app: ApplicationToDo) : ICacheNotes {

//TODO read write from disc

    var data: ModelNoteCollection? = null

    private var selectedNoteId: String? = null

    fun checkData(): ModelNoteCollection {

        if (data == null) {
            //todo try to read from disc
        }

        if (data == null) {
            data = ModelNoteCollection(null)
        }
        return data!!
    }


    override fun add(title: String?, body: String?) {
        if (title == null && body == null) return

        if (checkData().notes == null)
            checkData().notes = ArrayList()
        checkData().notes!!.add(ModelNote(UUID.randomUUID().toString(), title, body))

        saveData()
    }

    private fun saveData() {
        //TODO implement
    }

    override fun getItemsNum(): Int = checkData().notes?.size ?: 0

    override fun getItemTitleSingleLine(position: Int): String? {
        val note = checkData().notes?.opt(position) ?: return null
        var result = ""

        if (!TextUtils.isEmpty(note.title))
            result += note.title + " "
        if (!TextUtils.isEmpty(note.body))
            result += note.body
        return result
    }

    override fun setSelectedNote(adapterPosition: Int) {
        selectedNoteId = checkData().notes?.opt(adapterPosition)?.id
    }

    override fun getBodySelected(): String? = checkData().notes?.find { it.id.equals(selectedNoteId) }?.body

    override fun getTitleSelected(): String? = checkData().notes?.find { it.id.equals(selectedNoteId) }?.title

}
