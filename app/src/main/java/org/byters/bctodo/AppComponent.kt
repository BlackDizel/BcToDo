package org.byters.bctodo

import dagger.Component
import org.byters.bctodo.controller.AppModule
import org.byters.bctodo.view.presenter.PresenterActivityMain
import org.byters.bctodo.view.ui.activity.ActivityMain
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun inject(param: ActivityMain)
    fun inject(param: PresenterActivityMain)

}