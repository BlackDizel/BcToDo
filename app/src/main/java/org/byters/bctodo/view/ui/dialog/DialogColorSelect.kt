package org.byters.bctodo.view.ui.dialog

import android.content.Context
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import com.jaredrummler.android.colorpicker.ColorShape
import org.byters.bctodo.view.ui.activity.ActivityBase
import org.byters.bctodo.view.ui.dialog.callback.IDialogColorSelectListener
import java.lang.ref.WeakReference

class DialogColorSelect(
    context: Context,
    listener: IDialogColorSelectListener
) : DialogBase {

    private var dialog: ColorPickerDialog

    private var refListener: WeakReference<IDialogColorSelectListener>

    private var refContext: WeakReference<Context>

    init {
        refContext = WeakReference(context)
        refListener = WeakReference(listener)

        dialog = ColorPickerDialog.newBuilder()
            .setAllowCustom(false)
            .setColorShape(ColorShape.SQUARE)
            .setShowColorShades(false)
            .create()

        dialog.setColorPickerDialogListener(ListenerDialog())
    }

    override fun show() {
        if (refContext == null || refContext.get() == null) return
        dialog.show((refContext.get() as ActivityBase).supportFragmentManager, null)
    }

    override fun cancel() {
        dialog.dismiss()
    }


    inner class ListenerDialog : ColorPickerDialogListener {
        override fun onColorSelected(dialogId: Int, color: Int) {
            refListener?.get()?.onColorSelect(color)
        }

        override fun onDialogDismissed(dialogId: Int) {
        }

    }

}
