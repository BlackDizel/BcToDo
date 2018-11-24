package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.view.presenter.callback.IPresenterNoteViewListener
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterNoteView(app: ApplicationToDo) : IPresenterNoteView {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    private lateinit var refListener: WeakReference<IPresenterNoteViewListener>

    init {
        app.component.inject(this)
    }

    override fun setListener(listener: IPresenterNoteViewListener) {
        refListener = WeakReference(listener)
    }


    override fun onCreateView() {
        refListener.get()?.setData(cacheNotes.getTitleSelected(), cacheNotes.getBodySelected())
    }

}
