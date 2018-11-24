package org.byters.bctodo.controller

import dagger.Module
import dagger.Provides
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.CacheNotes
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.Navigator
import org.byters.bctodo.view.presenter.IPresenterActivityMain
import org.byters.bctodo.view.presenter.IPresenterListNotesAdapter
import org.byters.bctodo.view.presenter.PresenterActivityMain
import org.byters.bctodo.view.presenter.PresenterListNotesAdapter
import javax.inject.Singleton

@Module
class AppModule(val app: ApplicationToDo) {

    //region presenters

    @Provides
    @Singleton
    fun getPresenterActivityMain(): IPresenterActivityMain = PresenterActivityMain(app)

    @Provides
    @Singleton
    fun getPresenterListNotesAdapter(): IPresenterListNotesAdapter = PresenterListNotesAdapter(app)

    //endregion

    //region caches

    @Provides
    @Singleton
    fun getCacheNotes(): ICacheNotes = CacheNotes(app)

    //endregion


    @Provides
    @Singleton
    fun getNavigator(): INavigator = Navigator(app)

}