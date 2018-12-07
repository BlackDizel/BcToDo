package org.byters.bctodo.controller

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.device.CacheStorage
import org.byters.bctodo.controller.data.device.ICacheStorage
import org.byters.bctodo.controller.data.memorycache.*
import org.byters.bctodo.controller.data.util.HelperNotesSelected
import org.byters.bctodo.controller.data.util.IHelperNotesSelected
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.Navigator
import org.byters.bctodo.view.presenter.*
import org.byters.bctodo.view.utils.HelperDialog
import org.byters.bctodo.view.utils.HelperPopup
import org.byters.bctodo.view.utils.IHelperDialog
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
    fun getPresenterNoteEdit(): IPresenterNoteEdit = PresenterNoteEdit(app)

    @Provides
    @Singleton
    fun getPresenterNoteView(): IPresenterNoteView = PresenterNoteView(app)

    @Provides
    @Singleton
    fun getPresenterListNotes(): IPresenterListNotes = PresenterListNotes(app)

    @Provides
    @Singleton
    fun getPresenterTagsAdapter(): IPresenterTagsAdapter = PresenterTagsAdapter(app)

    @Provides
    @Singleton
    fun getPresenterNoteCreateTagsAdapter(): IPresenterNoteCreateTagsAdapter = PresenterNoteCreateTagsAdapter(app)

    @Provides
    @Singleton
    fun getPresenterDialogTagsAdapter(): IPresenterDialogTagListAdapter = PresenterDialogTagListAdapter(app)

    @Provides
    @Singleton
    fun getPresenterDialogFodlersAdapter(): IPresenterDialogFoldersAdapter = PresenterDialogFoldersAdapter(app)

    //endregion

    //region caches

    @Provides
    @Singleton
    fun getCacheNotes(): ICacheNotes = CacheNotes(app)

    @Provides
    @Singleton
    fun getCacheStorage(): ICacheStorage = CacheStorage(app)

    @Provides
    @Singleton
    fun getCacheInterfaceState(): ICacheInterfaceState = CacheInterfaceState(app)

    @Provides
    @Singleton
    fun getCacheTags(): ICacheTags = CacheTags(app)

    @Provides
    @Singleton
    fun getCacheNoteCreate(): ICacheNoteCreate = CacheNoteCreate(app)

    @Provides
    @Singleton
    fun getCacheFolders(): ICacheFolders = CacheFolders(app)
    //endregion


    @Provides
    @Singleton
    fun getNavigator(): INavigator = Navigator(app)

    @Provides
    @Singleton
    fun getHelperPopup(): IHelperPopup = HelperPopup(app)

    @Provides
    @Singleton
    fun getHelperDialogs(): IHelperDialog = HelperDialog(app)

    @Provides
    @Singleton
    fun getHelperNotesSelected(): IHelperNotesSelected = HelperNotesSelected(app)

    @Provides
    @Singleton
    fun getAppContext(): WeakReference<Context> = WeakReference(app)

    @Provides
    @Singleton
    fun getGson(): Gson = Gson()

}