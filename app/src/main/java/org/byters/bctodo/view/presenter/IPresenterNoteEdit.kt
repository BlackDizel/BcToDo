package org.byters.bctodo.view.presenter

import org.byters.bctodo.view.presenter.callback.IPresenterNoteEditListener

interface IPresenterNoteEdit {
    fun setListener(listener: IPresenterNoteEditListener)
    fun onClickSave(title: String, body: String)
    fun onCreateView()

}
