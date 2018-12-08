package org.byters.bctodo.view.presenter

import org.byters.bctodo.view.presenter.callback.IPresenterNoteCreateListener

interface IPresenterNoteCreate {
    fun setListener(listener: IPresenterNoteCreateListener)
    fun onClickSave(title: String, body: String)
    fun onCreateView()
    fun onClickFolder()
    fun onClickLabelColor()

}
