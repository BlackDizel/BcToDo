package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.view.INavigator
import javax.inject.Inject

class PresenterListNotesAdapter(app: ApplicationToDo) : IPresenterListNotesAdapter {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    @Inject
    lateinit var navigator: INavigator

    init {
        app.component.inject(this)
    }

    override fun getItemsNum(): Int = cacheNotes.getItemsNum()

    override fun getItemTitleSingleLine(position: Int): String? = cacheNotes.getItemTitleSingleLine(position)

    override fun onClick(adapterPosition: Int) {
        cacheNotes.setSelectedNote(adapterPosition)
        navigator.navigateNoteView()
    }
}
