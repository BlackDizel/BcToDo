package org.byters.bctodo.view.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterDialogFoldersAdapter
import org.byters.bctodo.view.ui.adapter.AdapterDialogFolders
import org.byters.bctodo.view.ui.adapter.callback.IDialogFolderAdapterListener
import javax.inject.Inject

class DialogFolders(context: Context) : DialogBase, View.OnClickListener {

    @Inject
    lateinit var presenterAdapterDialogFolders: IPresenterDialogFoldersAdapter

    private val dialog: Dialog

    private var listenerDialogAdapter: IDialogFolderAdapterListener

    init {
        (context.applicationContext as ApplicationToDo).component.inject(this)
        presenterAdapterDialogFolders.onCreate()

        listenerDialogAdapter = ListenerDialogAdapter()

        dialog = Dialog(context, R.style.themeDialogFullscreen)
        dialog.setContentView(R.layout.dialog_folders)
        initViews(dialog)
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initViews(dialog: Dialog) {
        dialog.findViewById<View>(R.id.flContent).setOnClickListener(this)
        dialog.findViewById<RecyclerView>(R.id.rvItems).apply {
            layoutManager = LinearLayoutManager(dialog.context)
            setHasFixedSize(true)
            adapter = AdapterDialogFolders(dialog.context.applicationContext as ApplicationToDo, listenerDialogAdapter)
        }
    }

    override fun cancel() {
        dialog.cancel()
    }

    override fun show() {
        dialog.show()
    }

    override fun onClick(v: View?) {
        cancel()
    }

    inner class ListenerDialogAdapter : IDialogFolderAdapterListener {
        override fun onClickItem() {
            cancel()
        }
    }

}
