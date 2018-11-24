package org.byters.bctodo.controller

import dagger.Module
import dagger.Provides
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.Navigator
import org.byters.bctodo.view.presenter.IPresenterActivityMain
import org.byters.bctodo.view.presenter.PresenterActivityMain
import javax.inject.Singleton

@Module
class AppModule(val app: ApplicationToDo) {

    @Provides
    @Singleton
    fun getPresenter(): IPresenterActivityMain = PresenterActivityMain(app)

    @Provides
    @Singleton
    fun getNavigator(): INavigator = Navigator(app)

}