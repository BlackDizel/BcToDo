package org.byters.bctodo

import dagger.Component
import org.byters.bctodo.controller.AppModule
import org.byters.bctodo.controller.data.device.CacheStorage
import org.byters.bctodo.controller.data.memorycache.CacheNotes
import org.byters.bctodo.controller.data.memorycache.CacheTags
import org.byters.bctodo.controller.data.util.HelperNotesSelected
import org.byters.bctodo.view.Navigator
import org.byters.bctodo.view.presenter.*
import org.byters.bctodo.view.ui.activity.ActivityMain
import org.byters.bctodo.view.ui.adapter.AdapterDialogListTags
import org.byters.bctodo.view.ui.adapter.AdapterListNotes
import org.byters.bctodo.view.ui.adapter.AdapterTags
import org.byters.bctodo.view.ui.adapter.AdapterTagsNoteCreate
import org.byters.bctodo.view.ui.fragment.FragmentListNotes
import org.byters.bctodo.view.ui.fragment.FragmentNoteCreate
import org.byters.bctodo.view.ui.fragment.FragmentNoteEdit
import org.byters.bctodo.view.ui.fragment.FragmentNoteView
import org.byters.bctodo.view.utils.HelperDialog
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
    fun inject(presenterListNotes: PresenterListNotes)
    fun inject(adapterTags: AdapterTags)
    fun inject(presenterTagsAdapter: PresenterTagsAdapter)
    fun inject(helperNotesSelected: HelperNotesSelected)
    fun inject(adapterTagsNoteCreate: AdapterTagsNoteCreate)
    fun inject(presenterNoteCreateTagsAdapter: PresenterNoteCreateTagsAdapter)
    fun inject(cacheTags: CacheTags)
    fun inject(navigator: Navigator)
    fun inject(helperDialog: HelperDialog)
    fun inject(adapterDialogListTags: AdapterDialogListTags)
    fun inject(presenterDialogTagListAdapter: PresenterDialogTagListAdapter)

}