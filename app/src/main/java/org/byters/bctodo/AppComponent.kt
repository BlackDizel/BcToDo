package org.byters.bctodo

import dagger.Component
import org.byters.bctodo.controller.AppModule
import org.byters.bctodo.controller.data.device.CacheStorage
import org.byters.bctodo.controller.data.memorycache.CacheNotes
import org.byters.bctodo.view.presenter.*
import org.byters.bctodo.view.ui.activity.ActivityMain
import org.byters.bctodo.view.ui.adapter.AdapterListNotes
import org.byters.bctodo.view.ui.fragment.FragmentListNotes
import org.byters.bctodo.view.ui.fragment.FragmentNoteCreate
import org.byters.bctodo.view.ui.fragment.FragmentNoteEdit
import org.byters.bctodo.view.ui.fragment.FragmentNoteView
import org.byters.bctodo.view.utils.HelperPopup
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun inject(param: ActivityMain)
    fun inject(param: PresenterActivityMain)
    fun inject(adapterListNotes: AdapterListNotes)
    fun inject(presenterListNotesAdapter: PresenterListNotesAdapter)
    fun inject(fragmentNoteCreate: FragmentNoteCreate)
    fun inject(presenterNoteCreate: PresenterNoteCreate)
    fun inject(fragmentListNotes: FragmentListNotes)
    fun inject(fragmentNoteView: FragmentNoteView)
    fun inject(presenterNoteView: PresenterNoteView)
    fun inject(helperPopup: HelperPopup)
    fun inject(fragmentNoteEdit: FragmentNoteEdit)
    fun inject(presenterNoteEdit: PresenterNoteEdit)
    fun inject(cacheStorage: CacheStorage)
    fun inject(cacheNotes: CacheNotes)

}