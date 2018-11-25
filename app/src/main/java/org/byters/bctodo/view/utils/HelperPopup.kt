package org.byters.bctodo.view.utils

import android.content.Context
import android.widget.Toast
import org.byters.bctodo.ApplicationToDo
import java.lang.ref.WeakReference
import javax.inject.Inject

class HelperPopup(app: ApplicationToDo) : IHelperPopup {

    @Inject
    lateinit var refContext: WeakReference<Context>

    private var toast: Toast? = null

    init {
        app.component.inject(this)
    }

    override fun showToast(resid: Int) {
        val context = refContext.get()
        if (context == null) return
        showToast(context.getString(resid))
    }

    override fun showToast(message: String) {
        val context = refContext.get()
        if (context == null) return

        toast?.cancel()

        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast?.show()


    }

}
