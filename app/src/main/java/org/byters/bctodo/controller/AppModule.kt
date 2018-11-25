package org.byters.bctodo.controller

import android.content.Context
import dagger.Module
import dagger.Provides
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.CacheNotes
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.Navigator
import org.byters.bctodo.view.presenter.*
import org.byters.bctodo.view.utils.HelperPopup
import org.byters.bctodo.view.utils.IHelperPopup
import java.lang.ref.WeakReference
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

    @Provides
    @Singleton
    fun getPresenterNoteCreate(): IPresenterNoteCreate = PresenterNoteCreate(app)

    @Provides
    @Singleton
    fun getPresenterNoteView(): IPresenterNoteView = PresenterNoteView(app)

    //endregion

    //region caches

    @Provides
    @Singleton
    fun getCacheNotes(): ICacheNotes = CacheNotes(app)

    //endregion


    @Provides
    @Singleton
    fun getNavigator(): INavigator = Navigator(app)

    @Provides
    @Singleton
    fun getHelperPopup(): IHelperPopup = HelperPopup(app)

    @Provides
    @Singleton
    fun getAppContext(): WeakReference<Context> = WeakReference(app)

}