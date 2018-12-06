package org.byters.bctodo.view.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import org.byters.bctodo.R
import org.byters.bctodo.view.ui.dialog.callback.IDialogFolderMoreListener
import java.lang.ref.WeakReference

class DialogFolderOptions(context: Context, listener: IDialogFolderMoreListener?) : DialogBase, View.OnClickListener,
    TextView.OnEditorActionListener {

    private val refListener: WeakReference<IDialogFolderMoreListener?>

    private val dialog: AlertDialog

    lateinit var vEdit: View

    lateinit var tvTitle: TextView

    init {

        refListener = WeakReference(listener)


        var view = LayoutInflater.from(context).inflate(R.layout.view_dialog_folder_options, null)
        initViews(view)
        dialog = AlertDialog.Builder(context).create()
        dialog.setView(view)
    }

    private fun initViews(view: View) {
        view.findViewById<View>(R.id.tvAdd).setOnClickListener(this)
        view.findViewById<View>(R.id.tvDelete).setOnClickListener(this)
        view.findViewById<View>(R.id.tvEdit).setOnClickListener(this)
        view.findViewById<View>(R.id.ivConfirm).setOnClickListener(this)
        vEdit = view.findViewById(R.id.flEdit)
        tvTitle = view.findViewById(R.id.etTitle)
        tvTitle.setOnEditorActionListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) return

        if (v.id == R.id.tvAdd) {
            refListener.get()?.onAdd()
            cancel()
        }

        if (v.id == R.id.tvDelete) {
            refListener.get()?.onDelete()
            cancel()
        }

        if (v.id == R.id.ivConfirm)
            checkEdit()

        if (v.id == R.id.tvEdit)
            vEdit.visibility = View.VISIBLE


    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        checkEdit()
        return true
    }

    private fun checkEdit() {
        if (TextUtils.isEmpty(tvTitle.text)) return
        refListener.get()?.onEdit(tvTitle.text.toString())
        cancel()
    }


    override fun show() {
        dialog.show()
    }

    override fun cancel() {
        dialog.cancel()
    }

}
