package org.byters.bctodo.view.ui.activity

import android.content.Intent
import android.os.Bundle
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterActivityMain
import org.byters.bctodo.view.presenter.IPresenterActivityMainListener
import javax.inject.Inject

class ActivityMain : ActivityBase() {

    @Inject
    lateinit var presenterActivityMain: IPresenterActivityMain

    lateinit var listenerPresenter: IPresenterActivityMainListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as ApplicationToDo).component.inject(this)

        listenerPresenter = ListenerPresenter()
        presenterActivityMain.setListener(listenerPresenter)
        presenterActivityMain.onCreate()

        setContentView(R.layout.activity_main)

        presenterActivityMain.onActivityCreate(this, R.id.flContent, supportFragmentManager);

    }

    inner class ListenerPresenter : IPresenterActivityMainListener {
        override fun restart() {
            this@ActivityMain.finish()
            this@ActivityMain.startActivity(Intent(this@ActivityMain, ActivityMain::class.java))
            this@ActivityMain.overridePendingTransition(0, 0)
        }

        override fun setTheme(resId: Int) {
            this@ActivityMain.setTheme(resId)
        }
    }

}