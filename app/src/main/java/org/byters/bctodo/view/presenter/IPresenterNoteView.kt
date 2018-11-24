package org.byters.bctodo.view.presenter

import org.byters.bctodo.view.presenter.callback.IPresenterNoteViewListener

interface IPresenterNoteView {
    fun setListener(listener: IPresenterNoteViewListener)
    fun onCreateView()

}
