package org.byters.bctodo.view.presenter.callback

import android.graphics.Typeface

interface IPresenterNoteViewListener {

    fun setData(title: String?, body: String?, font: Typeface)
    fun finish()
}
