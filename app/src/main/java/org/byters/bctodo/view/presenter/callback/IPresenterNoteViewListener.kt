package org.byters.bctodo.view.presenter.callback

interface IPresenterNoteViewListener {

    fun setData(title: String?, body: String?)
    fun finish()
}
