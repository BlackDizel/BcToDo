package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.view.presenter.callback.IPresenterNoteCreateListener
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterNoteCreate(app: ApplicationToDo) : IPresenterNoteCreate {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    lateinit var refListener: WeakReference<IPresenterNoteCreateListener>

    init {
        app.component.inject(this)
    }

    override fun onClickSave(title: String, body: String) {
        cacheNotes.add(title, body)
        refListener.get()?.finish()
    }


    override fun setListener(listener: IPresenterNoteCreateListener) {
        refListener = WeakReference(listener)
    }

}
