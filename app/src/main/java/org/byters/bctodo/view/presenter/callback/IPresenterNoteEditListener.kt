package org.byters.bctodo.view.presenter.callback

import android.graphics.Typeface

interface IPresenterNoteEditListener {

    fun finish()
    fun setData(titleSelected: String?, bodySelected: String?, font: Typeface)
}
