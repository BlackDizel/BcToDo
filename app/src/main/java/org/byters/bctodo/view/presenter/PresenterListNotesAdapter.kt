package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import javax.inject.Inject

class PresenterListNotesAdapter(app: ApplicationToDo) : IPresenterListNotesAdapter {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    init {
        app.component.inject(this)
    }

    override fun getItemsNum(): Int = cacheNotes.getItemsNum()

    override fun getItemTitleSingleLine(position: Int): String? = cacheNotes.getItemTitleSingleLine(position)

    override fun onClick(adapterPosition: Int) {
        //TODO navigate note
    }
}
