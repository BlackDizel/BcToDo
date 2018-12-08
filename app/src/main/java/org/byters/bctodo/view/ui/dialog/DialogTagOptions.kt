package org.byters.bctodo.view.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import org.byters.bctodo.R
import org.byters.bctodo.view.ui.adapter.ColorPaletteAdapter
import org.byters.bctodo.view.ui.dialog.callback.IDialogTagOptionsListener
import java.lang.ref.WeakReference

class DialogTagOptions(context: Context, listener: IDialogTagOptionsListener) : DialogBase, View.OnClickListener,
    TextView.OnEditorActionListener {

    lateinit var vEdit: View

    lateinit var tvTitle: TextView

    lateinit var gvColor: View
    lateinit var ivLabelColor: ImageView

    private var refListener: WeakReference<IDialogTagOptionsListener>? = null

    private val dialog: AlertDialog
    private var colorSelected: Int? = null

    init {
        refListener = WeakReference(listener)

        val view = LayoutInflater.from(context).inflate(R.layout.view_dialog_tag_options, null)
        initViews(view)
        dialog = AlertDialog.Builder(context).create()
        dialog.setView(view)
    }

    private fun initViews(view: View) {
        view.findViewById<View>(R.id.tvDelete).setOnClickListener(this)
        view.findViewById<View>(R.id.tvEdit).setOnClickListener(this)
        view.findViewById<View>(R.id.ivConfirm).setOnClickListener(this)
        view.findViewById<View>(R.id.flLabelColor).setOnClickListener(this)

        vEdit = view.findViewById(R.id.flEdit)
        tvTitle = view.findViewById(R.id.etTitle)
        gvColor = view.findViewById(R.id.gvColor)
        ivLabelColor = view.findViewById(R.id.ivLabelColor)


        tvTitle.setOnEditorActionListener(this)

        initColors(view)

    }

    private fun initColors(view: View) {
        val gridView = view.findViewById<GridView>(R.id.gvColor)
        gridView.adapter = ColorPaletteAdapter(ListenerColorSelect(), ColorPickerDialog.MATERIAL_COLORS)
    }

    inner class ListenerColorSelect : ColorPaletteAdapter.OnColorSelectedListener {
        override fun onColorSelected(color: Int) {
            colorSelected = color
            ivLabelColor.setColorFilter(colorSelected ?: Color.TRANSPARENT)
        }

    }

    override fun onClick(v: View?) {
        if (v == null) return

        if (v.id == R.id.tvDelete) {
            refListener?.get()?.onRemove()
            cancel()
        }

        if (v.id == R.id.ivConfirm)
            checkEdit()

        if (v.id == R.id.tvEdit)
            vEdit.visibility = View.VISIBLE

        if (v.id == R.id.flLabelColor)
            gvColor.visibility = View.VISIBLE

    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        checkEdit()
        return true
    }

    private fun checkEdit() {
        if (TextUtils.isEmpty(tvTitle.text)) return
        refListener?.get()?.onEdit(tvTitle.text.toString(), colorSelected)
        cancel()
    }


    override fun cancel() {
        dialog.cancel()
    }

    override fun show() {
        dialog.show()
    }


}
