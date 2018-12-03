package org.byters.bctodo.view.ui.view.callback

interface IViewSearchListener {

    fun onHide()
    fun onShow()
    fun onQuery(query: String?)
}
