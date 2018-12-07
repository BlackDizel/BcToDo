package org.byters.bctodo.view.presenter

import org.byters.bctodo.view.presenter.callback.IPresenterListNotesListener

interface IPresenterListNotes {
    fun onClickAdd()
    fun onClickStyle()
    fun onClickTheme()
    fun onClickFont()
    fun onClickTags()
    fun setListener(listenerPresenter: IPresenterListNotesListener)
    fun onQueryEmpty()
    fun onQuery(query: String?)
    fun onClickFolders()
    fun onClickPathCancel()
    fun onCreateView()

}
