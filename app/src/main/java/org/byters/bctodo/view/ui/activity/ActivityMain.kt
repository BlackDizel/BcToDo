package org.byters.bctodo.view.ui.activity

import android.os.Bundle
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterActivityMain
import javax.inject.Inject

class ActivityMain : ActivityBase() {

    @Inject
    lateinit var presenterActivityMain: IPresenterActivityMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as ApplicationToDo).component.inject(this)

        presenterActivityMain.onActivityCreate(R.id.flContent, supportFragmentManager);

    }

}