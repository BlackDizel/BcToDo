package org.byters.bctodo.view.presenter.callback

interface IPresenterListNotesListener {

    fun setVisibilityTags(isVisible: Boolean)
    fun setPathVisible(isVisible: Boolean, itemTitle: String)
}
