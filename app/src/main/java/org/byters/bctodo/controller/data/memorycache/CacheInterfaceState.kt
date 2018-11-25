package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.ApplicationToDo

import org.byters.bctodo.controller.data.memorycache.callback.ICacheInterfaceStateListener
import org.byters.bctodo.model.StyleEnum
import java.lang.ref.WeakReference

class CacheInterfaceState(app: ApplicationToDo) : ICacheInterfaceState {

    private var refListener: WeakReference<ICacheInterfaceStateListener>? = null

    private var style: Int = 0

    override fun setStyleNext() {
        this.style += 1
        style = style % 3
        refListener?.get()?.onStyleUpdate()
    }

    override fun setListener(listener: ICacheInterfaceStateListener) {
        refListener = WeakReference(listener)
    }

    override fun getStyle(): StyleEnum =
        if (style == 0) StyleEnum.SMALL
        else if (style == 1) StyleEnum.MEDIUM
        else StyleEnum.FULL

}
