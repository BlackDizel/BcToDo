package org.byters.bctodo.view.presenter.callback

interface IPresenterNoteEditListener {

    fun finish()
    fun setData(titleSelected: String?, bodySelected: String?)
}
